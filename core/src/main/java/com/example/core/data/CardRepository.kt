package com.example.core.data

/**
 *an abstract repository for managing data sources streams.it will be implemented by framework layer
 */
class CardRepository(private val cardDatasource: CardDatasource) {
    suspend fun getCards()=cardDatasource.getCards()
}