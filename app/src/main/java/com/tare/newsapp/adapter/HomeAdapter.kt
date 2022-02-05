package com.tare.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.nativead.NativeAd
import com.tare.newsapp.R
import com.tare.newsapp.databinding.ItemAdBinding
import com.tare.newsapp.databinding.ItemNewsBinding
import com.tare.newsapp.pojo.entities.Article
import com.tare.newsapp.ui.HomeViewModel
import com.tare.newsapp.utils.DiffUtilHome

class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val _isAd = 0
    private val _notAd = 1
    private var objectList: List<Any> = listOf()
    lateinit var homeViewModel: HomeViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == _isAd) {
            val binding: ItemAdBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_ad, parent, false
            )
            AdViewHolder(binding)
        } else {
            val binding: ItemNewsBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_news, parent, false
            )
            NewsViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == _isAd) {
            val viewHolder = holder as AdViewHolder
            viewHolder.bindAd(objectList[position] as NativeAd)
        } else {
            val viewHolder = holder as NewsViewHolder
            viewHolder.bind(objectList[position] as Article, homeViewModel)
        }
    }

    override fun getItemCount(): Int = objectList.size

    override fun getItemViewType(position: Int): Int {
        return if (objectList[position] is NativeAd) {
            _isAd
        } else {
            _notAd
        }
    }

    fun update(updated: List<Any>?) {
        val old = objectList
        objectList = updated ?: listOf()
        DiffUtil.calculateDiff(DiffUtilHome(old, objectList)).dispatchUpdatesTo(this)
    }
}