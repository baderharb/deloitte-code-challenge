package com.example.deloittecodechallenge.utils.prefs

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.example.deloittecodechallenge.utils.annotaion.NetworkSharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    @Singleton
    @NetworkSharedPreferences
    fun provideNetworkSharedPreferences(context: Application): SharedPreferences {
        return context.getSharedPreferences(
            "com.example.deloittecodechallenge",
            Context.MODE_PRIVATE
        )
    }

    @Provides
    @Singleton
    fun providePrefs(sharedPreferences: SharedPreferences): Prefs {
        return PrefsImpl(sharedPreferences)
    }
}
