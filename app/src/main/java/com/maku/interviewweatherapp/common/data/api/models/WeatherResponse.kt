package com.maku.interviewweatherapp.common.data.api.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    @field:Json(name = "cnt")
    val cnt: Int,
    @field:Json(name = "list")
    val list: List<CityWeather>
)