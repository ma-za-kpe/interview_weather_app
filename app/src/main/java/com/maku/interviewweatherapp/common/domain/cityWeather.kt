package com.maku.interviewweatherapp.common.domain

data class cityWeather (
    val clouds: clouds,
    val coord: coord,
    val dt: Int?,
    val id: Int,
    val main: main,
    val name: String?,
    val sys: sys,
    val visibility: Int?,
    val weather: String,
    val wind: wind,
    val favorite: Boolean
)

data class clouds(
    val all: Int?
)

data class coord(
    val lat: Double?,
    val lon: Double?
)

data class main(
    val feelsLike: Double?,
    val grndLevel: Int?,
    val humidity: Int?,
    val pressure: Int?,
    val seaLevel: Int?,
    val temp: Double?,
    val tempMax: Double?,
    val tempMin: Double?
)

data class sys(
    val country: String?,
    val sunrise: Int?,
    val sunset: Int?,
    val timezone: Int?
)

data class weather(
    val description: String?,
    val icon: String?,
    val id: Int?,
    val main: String?
)

data class wind(
    val deg: Int?,
    val speed: Double?
)