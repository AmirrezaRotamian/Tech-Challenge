package com.example.rayakavosh_task.framework.di

import com.example.core.data.CardDatasource
import com.example.core.data.CardRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class HiltDataSource {
    @Binds
    abstract fun bindCardDataSource(
            cardcardRepository: CardRepository
    ):CardDatasource
}