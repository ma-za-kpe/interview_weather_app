package com.maku.interviewweatherapp.common.data.cache

import com.maku.interviewweatherapp.common.data.cache.doa.WeatherDao
import com.maku.interviewweatherapp.common.data.cache.entities.WeatherEntity
import io.reactivex.Flowable
import javax.inject.Inject

class RoomCache @Inject constructor(
    private val weatherDao: WeatherDao
) : Cache {
    override suspend fun storeWeather(weather: List<WeatherEntity>) {
        weatherDao.insertWeather(weather)
    }

    override fun getWeather(): Flowable<List<WeatherEntity>> {
       return weatherDao.readWeather()
    }
}