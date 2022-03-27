package com.maku.interviewweatherapp.common.data.cache

import com.maku.interviewweatherapp.common.data.api.models.CityWeather
import com.maku.interviewweatherapp.common.data.cache.doa.WeatherDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomCache @Inject constructor(
    private val weatherDao: WeatherDao
) : Cache {
    override suspend fun storeWeather(weather: CityWeather) {
        weatherDao.insertWeather(weather)
    }

    override suspend fun favWeather(fav: Boolean, id: Int) {
        weatherDao.favWeather(fav, id)
    }

    override fun getWeather(): Flow<List<CityWeather>> {
       return weatherDao.readWeather()
    }
}