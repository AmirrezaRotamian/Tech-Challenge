package com.example.rayakavosh_task.framework.di

import com.example.core.data.CardRepository
import com.example.core.domain.Card
import com.example.core.domain.CodeType
import com.example.rayakavosh_task.framework.network.CardApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class HiltCardModule {

    @Provides
    @Singleton
    fun provideAuthInterceptorOkHttpClient(): OkHttpClient {
        val httpLogger =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
                .addInterceptor(httpLogger)
                .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): CardApi {
        val moshi = Moshi.Builder()
                .add(
                        PolymorphicJsonAdapterFactory.of(Card::class.java,"code")
                                .withSubtype(Card.PictureCard::class.java,CodeType.IMAGE.name)
                                .withSubtype(Card.VibratorCard::class.java,CodeType.VIBRATOR.name)
                                .withSubtype(Card.SoundCard::class.java,CodeType.VIBRATOR.name)
                ).add(KotlinJsonAdapterFactory())
                .build()

        return Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(CardApi.BASE_URL)
                .build()
                .create(CardApi::class.java)
    }


}