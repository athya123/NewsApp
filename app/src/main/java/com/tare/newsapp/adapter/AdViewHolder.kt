package com.tare.newsapp.adapter

import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.nativead.NativeAd
import com.tare.newsapp.databinding.ItemAdBinding


class AdViewHolder(private val binding: ItemAdBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindAd(nativeAd: NativeAd?) {
        binding.nativeAd = nativeAd
        binding.executePendingBindings()
    }
}