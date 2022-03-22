package com.maku.interviewweatherapp.common.data.api.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sys(
    @field:Json(name = "country")
    val country: String,
    @field:Json(name = "sunrise")
    val sunrise: Int,
    @field:Json(name = "sunset")
    val sunset: Int,
    @field:Json(name = "timezone")
    val timezone: Int
)