package com.maku.interviewweatherapp.common.data.api.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Wind(
    @field:Json(name = "deg")
    val deg: Int,
    @field:Json(name = "speed")
    val speed: Double
)