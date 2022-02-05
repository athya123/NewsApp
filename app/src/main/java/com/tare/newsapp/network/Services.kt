package com.tare.newsapp.network

import com.tare.newsapp.pojo.response.ResponseGetNews
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {

    @GET("v2/top-headlines")
    fun fetchNews(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String
    ): Observable<ResponseGetNews>

}