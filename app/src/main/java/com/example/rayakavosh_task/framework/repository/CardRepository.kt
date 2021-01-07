package com.example.rayakavosh_task.framework.repository

import com.example.core.data.CardDatasource
import com.example.core.domain.Card
import com.example.rayakavosh_task.framework.network.CardApi
import javax.inject.Inject

/**
 * an implementation of Card Datasource in the data layer
 */
class CardRepository @Inject constructor(
        private val cardApi: CardApi
):CardDatasource {
    override suspend fun getCards(): List<Card> {
        return cardApi.getCards().body()!!
    }

}