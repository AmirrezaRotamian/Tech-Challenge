package com.example.rayakavosh_task.presentation.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.core.domain.Card
import com.example.core.domain.JsonResponse
import com.example.rayakavosh_task.framework.InterActors
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.launch
import kotlin.random.Random

/**
 * this View model provides data for UI
 */
@ActivityRetainedScoped
class CardViewModel @ViewModelInject constructor(
        private val interActors: InterActors
) : ViewModel() {

    private val _cardListLiveData = MediatorLiveData<JsonResponse>()

    private val _cardItemLiveData=MutableLiveData<Card>()
    val cardItem:LiveData<Card>
    get() = _cardItemLiveData
    /**
     * gets list of cards via interActors
     */
    fun getCards() {
        viewModelScope.launch {
            _cardListLiveData.value =
                    interActors.getCards.invoke()
        }
    }

    fun pickrandomCard() {
        getCards()
        _cardItemLiveData.value=_cardListLiveData.value?.cards?.random()
    }

}