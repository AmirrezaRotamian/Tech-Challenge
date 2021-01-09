package com.example.core.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * a model class for deserializing card types data
 */
@JsonClass(generateAdapter = true)
sealed class Card constructor(
        @Json (name = "code")
        val code: CodeType
) {
    @JsonClass(generateAdapter = true)
    data class PictureCard(
        val title:String,
        val description :String,
        val image:String,
        val tag:String
    ):Card(CodeType.IMAGE)
    @JsonClass(generateAdapter = true)
    data class VibratorCard(
        val title:String,
        val description :String,
        val tag:String
    ):Card(CodeType.VIBRATOR)
    @JsonClass(generateAdapter = true)
    data class SoundCard(
        val title:String,
        val description :String,
        val sound :String,
        val tag:String
    ):Card(CodeType.SOUND)
}