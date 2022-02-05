package com.tare.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.webkit.WebView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.google.android.gms.ads.*
import com.tare.newsapp.R
import com.tare.newsapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            viewModel = homeViewModel
            lifecycleOwner = this@HomeActivity
        }
        webView = binding.webView
        val country = resources.configuration.locales[0].country
        homeViewModel.fetchNews(country)
        MobileAds.setRequestConfiguration(
            RequestConfiguration.Builder()
                .setTestDeviceIds(listOf("97015A542488682AEC52D8A701AA2C23")).build()
        )
        MobileAds.initialize(applicationContext)
        subscribeObservers()
    }

    override fun onBackPressed() {
        if (webView.visibility == View.VISIBLE) {
            webView.startAnimation(
                AnimationUtils.loadAnimation(
                    this,
                    android.R.anim.slide_out_right
                )
            )
            webView.visibility = View.INVISIBLE
        } else
            super.onBackPressed()

    }

    private fun subscribeObservers() {
        homeViewModel.adStatus.observe(this) {
            if (it == true) {
                homeViewModel.adLoader.observe(this) { adLoader ->
                    if (!adLoader.isLoading) {
                        homeViewModel.initAds()
                    }
                }
            }
        }
        homeViewModel.adList.observe(this) {
            homeViewModel.mixLists()
        }
        homeViewModel.newsList.observe(this) {
            homeViewModel.mixLists()
        }
    }
}
