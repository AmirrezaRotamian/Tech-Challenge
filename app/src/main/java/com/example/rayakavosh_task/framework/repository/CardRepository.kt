package com.example.rayakavosh_task.framework.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.core.data.CardDatasource
import com.example.core.domain.Card
import com.example.core.domain.JsonResponse
import com.example.rayakavosh_task.framework.network.CardApi
import javax.inject.Inject

/**
 * an implementation of Card Datasource in the data layer
 */
class CardRepository @Inject constructor(
        private val cardApi: CardApi
):CardDatasource {
    override suspend fun getCards():JsonResponse {
        return cardApi.getCards()
    }

}