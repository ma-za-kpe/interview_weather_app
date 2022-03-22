package com.maku.interviewweatherapp.common.data.api.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CityWeather(
    @field:Json(name = "clouds")
    val clouds: Clouds,
    @field:Json(name = "coord")
    val coord: Coord,
    @field:Json(name = "dt")
    val dt: Int,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "main")
    val main: Main,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "sys")
    val sys: Sys,
    @field:Json(name = "visibility")
    val visibility: Int,
    @field:Json(name = "weather")
    val weather: List<Weather>,
    @field:Json(name = "wind")
    val wind: Wind
)