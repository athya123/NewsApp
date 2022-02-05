package com.tare.newsapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.nativead.NativeAd
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.tare.newsapp.network.Services
import com.tare.newsapp.pojo.entities.Article
import com.tare.newsapp.utils.Constants
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val networkServices: Services,
    private val constants: Constants,
    private val adLoaderBuilder: AdLoader.Builder,
    private val remoteConfig: FirebaseRemoteConfig,
) {

    private val _responseGetNews = MutableLiveData<List<Article>>()
    val responseGetNews: LiveData<List<Article>>
        get() = _responseGetNews

    private val tempList: ArrayList<NativeAd> = arrayListOf()
    private val _adsList = MutableLiveData<List<NativeAd>>()
    val adsList: LiveData<List<NativeAd>>
        get() = _adsList

    private val _adLoader = MutableLiveData<AdLoader>()
    val adLoader: LiveData<AdLoader>
        get() = _adLoader

    private val _adStatus = MutableLiveData<Boolean>()
    val adStatus: LiveData<Boolean>
        get() = _adStatus

    fun fetchNews(query: String) {
        networkServices.fetchNews(constants.APIKEY, query)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _responseGetNews.postValue(it.articles)
            }, {
                Log.e("ERROR", "FOLLOWING ERROR OCCURRED:", it)
            })
    }

    fun getAdLoader() {
        val adLoader = adLoaderBuilder.forNativeAd {
            tempList.add(it)
            _adsList.postValue(tempList)
        }.build()
        _adLoader.postValue(adLoader)
    }

    fun activateFetched() {
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _adStatus.postValue(remoteConfig.getBoolean("isAdEnabled"))
                } else {
                    Log.d("FETCH FAILED", "OK")
                }
            }
    }
}