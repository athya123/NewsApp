package com.tare.newsapp.utils

import android.view.View
import android.view.animation.AnimationUtils
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.ads.nativetemplates.NativeTemplateStyle
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.nativead.NativeAd
import com.tare.newsapp.adapter.HomeAdapter
import com.tare.newsapp.pojo.entities.Article
import com.tare.newsapp.ui.HomeViewModel

@BindingAdapter("setDate")
fun bindDate(textView: TextView, date: String?) {
    if (!date.isNullOrEmpty()) {
        textView.text = DateHelper.formatDate(date)
    }
}

@BindingAdapter("setImage")
fun bindImage(imageView: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }
}

@BindingAdapter("setList", "setViewModel", requireAll = true)
fun bindRecyclerView(recyclerView: RecyclerView, list: List<Any>?, viewModel: HomeViewModel) {
    val adapter = getOrSetAdapter(recyclerView)
    adapter.update(list)
    adapter.homeViewModel = viewModel
}

private fun getOrSetAdapter(recyclerView: RecyclerView): HomeAdapter {
    return if (recyclerView.adapter != null && recyclerView.adapter is HomeAdapter) {
        recyclerView.adapter as HomeAdapter
    } else {
        val adapter = HomeAdapter()
        recyclerView.adapter = adapter
        adapter
    }
}

@BindingAdapter("openNews")
fun bindNews(webView: WebView, item: Article?) {
    item?.let {
        webView.startAnimation(
            AnimationUtils.loadAnimation(
                webView.context,
                android.R.anim.slide_in_left
            )
        )
        webView.settings.javaScriptEnabled = true
        webView.settings.loadsImagesAutomatically = true
        webView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        webView.loadUrl(item.url)
        webView.visibility = View.VISIBLE
    }
}

@BindingAdapter("setAd")
fun bindAds(templateView: TemplateView, adList: NativeAd?) {
    val styles = NativeTemplateStyle.Builder().build()
    templateView.setStyles(styles)
    adList?.let {
        templateView.setNativeAd(it)
    }
}

