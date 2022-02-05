package com.tare.newsapp.di

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Singleton
    @Provides
    fun provideRemoteConfig(): FirebaseRemoteConfig {
        return FirebaseRemoteConfig.getInstance()
    }


    @Singleton
    @Provides
    fun provideFirebaseRemoteSettings(): FirebaseRemoteConfigSettings {
        return FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(20)
            .build()
    }
}