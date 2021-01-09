package com.example.rayakavosh_task.framework

import com.example.core.interactors.GetCards

/**
 * this data class prepare connects viewModel with repository layer
 */
data class InterActors(
        val getCards: GetCards
)