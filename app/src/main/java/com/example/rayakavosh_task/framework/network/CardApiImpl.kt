package com.example.rayakavosh_task.framework.network

import com.example.core.domain.Card
import com.example.core.domain.JsonResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import javax.inject.Inject

/**
 * a fake api implementation of [CardApi]
 */
class CardApiImpl( val json: String) : CardApi {
    override suspend fun getCards(): JsonResponse {

        return parseJson(json = json)
    }

    /**
     * Parses the Input Json Object with [Moshi]
     */
    private fun parseJson(json: String): JsonResponse {

        //it is a polymorphic adapter factory for serialize and deserializing our sealed class
        val adapterFactory = PolymorphicJsonAdapterFactory.of(Card::class.java, "code")
            .withSubtype(Card.PictureCard::class.java, "0")
            .withSubtype(Card.VibratorCard::class.java, "1")
            .withSubtype(Card.SoundCard::class.java, "2")

        val moshi = Moshi.Builder().add(adapterFactory)
            .add(KotlinJsonAdapterFactory()).build()
        val type = Types.newParameterizedType(JsonResponse::class.java, Card::class.java)
        val adapter: JsonAdapter<JsonResponse> = moshi.adapter(type)

        return adapter.fromJson(json)!!
    }

}


