package com.maku.interviewweatherapp.common.data.cache

import com.maku.interviewweatherapp.common.data.cache.entities.CacheWeather
import com.maku.interviewweatherapp.common.data.cache.entities.FavoriteWeatherEntity
import com.maku.interviewweatherapp.common.data.cache.entities.WeatherEntity
import kotlinx.coroutines.flow.Flow

interface Cache {
  suspend fun storeWeather(weather: CacheWeather)
  fun getWeather(): Flow<List<CacheWeather>>

  suspend fun storeFavWeather(weather: FavoriteWeatherEntity)
  fun getFavWeather(): Flow<List<FavoriteWeatherEntity>>
}