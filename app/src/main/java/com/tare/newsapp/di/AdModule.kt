package com.tare.newsapp.di

import android.content.Context
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.nativead.NativeAdOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AdModule {

    @Singleton
    @Provides
    fun provideNativeAdOptions(): NativeAdOptions {
        return NativeAdOptions.Builder().build()
    }

    @Singleton
    @Provides
    fun provideAdRequest(): AdRequest {
        return AdRequest.Builder().build()
    }

    @Singleton
    @Provides
    fun providerAdLoaderBuilder(
        @ApplicationContext context: Context,
        nativeAdOptions: NativeAdOptions
    ): AdLoader.Builder {
        return AdLoader.Builder(context, "ca-app-pub-3940256099942544/2247696110")
            .withNativeAdOptions(nativeAdOptions)
    }
}