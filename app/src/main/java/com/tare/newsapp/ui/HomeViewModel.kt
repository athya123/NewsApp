package com.tare.newsapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.ads.AdRequest
import com.tare.newsapp.pojo.entities.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val homeRepository: HomeRepository,
    private val adRequest: AdRequest
) : ViewModel() {

    init {
        homeRepository.activateFetched()
        homeRepository.getAdLoader()
    }

    val itemList = MutableLiveData<List<Any>>()
    val clickedNews = MutableLiveData<Article>()
    val newsList = homeRepository.responseGetNews
    var adList = homeRepository.adsList
    var adLoader = homeRepository.adLoader
    val adStatus = homeRepository.adStatus

    fun fetchNews(country: String) {
        val count = country.lowercase()
        homeRepository.fetchNews(count)
    }

    fun onClickNews(item: Article) {
        clickedNews.postValue(item)
    }

    fun mixLists() {
        val list = arrayListOf<Any>()
        newsList.value?.let { list.addAll(it) }
        adList.value?.let {
            if(it.size == 3){
            list.add(0, it[0])
            list.add(list.lastIndex + 1, it[2])
            list.add(list.lastIndex / 2, it[1])
            }
        }
        itemList.postValue(list)
    }

    fun initAds() {
        adLoader.value?.loadAds(adRequest, 3)
    }
}