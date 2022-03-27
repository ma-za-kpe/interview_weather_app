package com.maku.interviewweatherapp.common.data.cache

import com.maku.interviewweatherapp.common.data.api.models.CityWeather
import kotlinx.coroutines.flow.Flow

interface Cache {
  suspend fun storeWeather(weather: CityWeather)
  suspend fun favWeather(fav: Boolean, id: Int)
  fun getWeather(): Flow<List<CityWeather>>
}