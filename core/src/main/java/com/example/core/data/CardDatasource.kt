package com.example.core.data

import com.example.core.domain.Card

/**
 * an abstract datasource to be used by repository and be implemented by framework layer
 * */
interface CardDatasource {
    suspend fun getCards():List<Card>
}