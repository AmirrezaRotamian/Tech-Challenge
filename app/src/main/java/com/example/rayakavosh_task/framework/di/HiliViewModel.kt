package com.example.rayakavosh_task.framework.di

import com.example.core.data.CardDatasource
import com.example.core.data.CardRepository
import com.example.core.interactors.GetCards
import com.example.rayakavosh_task.framework.InterActors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * this module provides dependencies for our viewModel
 */
@InstallIn(ActivityComponent::class)
@Module
class HiliViewModel {

    /**
     * provides interActors for viewModel
     */
    @Provides
    fun providesInterActors(cardRepository: CardRepository) =
        InterActors(GetCards(cardRepository))

    /**
     * provides repository for viewModel
     */
    @Provides
    fun providesCardRepository(cardDatasource: CardDatasource) = CardRepository(cardDatasource)
}