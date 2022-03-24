package com.maku.interviewweatherapp.common.data.cache

import com.maku.interviewweatherapp.common.data.cache.doa.WeatherDao
import com.maku.interviewweatherapp.common.data.cache.entities.CacheWeather
import com.maku.interviewweatherapp.common.data.cache.entities.FavoriteWeatherEntity
import com.maku.interviewweatherapp.common.data.cache.entities.WeatherEntity
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomCache @Inject constructor(
    private val weatherDao: WeatherDao
) : Cache {
    override suspend fun storeWeather(weather: CacheWeather) {
        weatherDao.insertWeather(weather)
    }

    override fun getWeather(): Flow<List<CacheWeather>> {
       return weatherDao.readWeather()
    }

    override suspend fun storeFavWeather(weather: FavoriteWeatherEntity) {
        weatherDao.insertFavWeather(weather)
    }

    override fun getFavWeather(): Flow<List<FavoriteWeatherEntity>> {
       return weatherDao.readFavWeather()
    }
}