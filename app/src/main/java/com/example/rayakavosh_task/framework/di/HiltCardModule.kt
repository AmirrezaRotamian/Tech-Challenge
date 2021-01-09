package com.example.rayakavosh_task.framework.di

import android.content.Context
import com.example.rayakavosh_task.framework.getJsonDataFromAsset
import com.example.rayakavosh_task.framework.network.CardApi
import com.example.rayakavosh_task.framework.network.CardApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * a Hilt module for providing dependencies
 */
@InstallIn(ApplicationComponent::class)
@Module
class HiltCardModule {

    /**
     * provides a fake Api of [CardApi]
     */
    @Singleton
    @Provides
    fun provideApi(json: String): CardApi = CardApiImpl(json)

    /**
     * provides local Json for our fake Api
     */
    @Singleton
    @Provides
    fun provideJson(@ApplicationContext context:Context):String =getJsonDataFromAsset(context,"tempelate.json")!!
}