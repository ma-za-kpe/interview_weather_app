package com.maku.interviewweatherapp.common.data.api.models


import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("cnt")
    val cnt: Int,
    @SerializedName("list")
    val list: List<CityWeather>
)