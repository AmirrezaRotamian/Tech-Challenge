package com.example.rayakavosh_task.framework.network

import com.example.core.domain.Card
import retrofit2.Response
import retrofit2.http.GET

interface CardApi {
    @GET("tempelate.json")
    suspend fun getCards(): Response<List<Card>>

    companion object {

        const val BASE_URL = "https://raw.githubusercontent.com/AmirrezaRotamian/Tech-Challenge/master/"
    }


}