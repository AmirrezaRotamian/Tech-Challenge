package com.example.core.interactors

import com.example.core.data.CardRepository

/**
 * a use case to be called from presentation layer.it connects two layers of app: presentation and data layer
 */
class GetCards constructor(private val cardRepository: CardRepository){
    /**
     * this method starts the repository for managing data
     */
    suspend operator fun invoke()=cardRepository.getCards()
}