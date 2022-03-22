package com.maku.interviewweatherapp.common.data.repo

import com.maku.interviewweatherapp.common.data.api.models.CityWeather
import com.maku.interviewweatherapp.common.data.api.models.WeatherResponse
import com.maku.interviewweatherapp.common.data.cache.entities.WeatherEntity
import io.reactivex.Flowable

interface WeatherRepo {
    fun getWeather(): Flowable<List<WeatherEntity>>
    suspend fun storeWeather(weather: List<WeatherEntity>)
    suspend fun requestWeatherData(idString: String): WeatherResponse
}