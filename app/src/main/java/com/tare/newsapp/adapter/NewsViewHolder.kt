package com.tare.newsapp.adapter

import androidx.recyclerview.widget.RecyclerView
import com.tare.newsapp.databinding.ItemNewsBinding
import com.tare.newsapp.pojo.entities.Article
import com.tare.newsapp.ui.HomeViewModel

class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(currentItem: Article, homeViewModel: HomeViewModel) {
        binding.item = currentItem
        binding.viewModel = homeViewModel
        binding.executePendingBindings()
    }
}