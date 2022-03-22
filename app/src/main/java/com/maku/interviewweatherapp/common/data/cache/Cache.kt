package com.maku.interviewweatherapp.common.data.cache

import com.maku.interviewweatherapp.common.data.cache.entities.WeatherEntity
import io.reactivex.Flowable

interface Cache {
  suspend fun storeWeather(weather: List<WeatherEntity>)

  fun getWeather(): Flowable<List<WeatherEntity>>

}