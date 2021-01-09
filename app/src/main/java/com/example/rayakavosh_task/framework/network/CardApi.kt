package com.example.rayakavosh_task.framework.network

import com.example.core.domain.JsonResponse

/**
 * an api for retrieving Card's data
 */
interface CardApi {

    /**
     * gets list of cards in a wrapped object by [JsonResponse]
     */
    suspend fun getCards(): JsonResponse

    companion object {
        //for retrofit it will the base Url
        const val BASE_URL = "https://raw.githubusercontent.com/AmirrezaRotamian/Tech-Challenge/master/"
    }


}