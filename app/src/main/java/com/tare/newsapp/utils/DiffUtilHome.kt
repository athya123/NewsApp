package com.tare.newsapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.google.android.gms.ads.nativead.NativeAd

class DiffUtilHome(private val oldList: List<Any>, private val newList: List<Any>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (oldList[oldItemPosition] is NativeAd == newList[newItemPosition] is NativeAd ||
                oldList[oldItemPosition] !is NativeAd == newList[newItemPosition] !is NativeAd)
    }
}