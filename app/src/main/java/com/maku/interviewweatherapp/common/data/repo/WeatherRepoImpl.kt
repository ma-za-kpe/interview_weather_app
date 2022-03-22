package com.maku.interviewweatherapp.common.data.repo

import com.maku.interviewweatherapp.common.data.api.WeatherAPI
import com.maku.interviewweatherapp.common.data.api.models.CityWeather
import com.maku.interviewweatherapp.common.data.api.models.WeatherResponse
import com.maku.interviewweatherapp.common.data.cache.Cache
import com.maku.interviewweatherapp.common.data.cache.entities.WeatherEntity
import com.maku.interviewweatherapp.common.utils.NetworkException
import io.reactivex.Flowable
import retrofit2.HttpException
import javax.inject.Inject

class WeatherRepoImpl @Inject constructor(
    private val api: WeatherAPI, // remote
    private val cache: Cache, // local
)  : WeatherRepo {
    override fun getWeather(): Flowable<List<WeatherEntity>> {
        return cache.getWeather()
            .distinctUntilChanged()
            .map { animalList ->
                animalList
            }
    }

    override suspend fun storeWeather(weather: List<WeatherEntity>) {
        cache.storeWeather(weather)
    }

    // city list here: http://web.archive.org/web/20180619015316/http://openweathermap.org/help/city_list.txt
    override suspend fun requestWeatherData(idString: String): WeatherResponse {
        try {
            return api.getWeatherFromCities("235039,184745,232422,4164143,4348460,6544881,2161311," +
                    "161325,186301,184622,2634894,5124925,5161693,971421,5144089,360630,3369157,524901," +
                    "703448,2643743")
        } catch (exception: HttpException) {
            throw NetworkException(exception.message ?: "Code ${exception.code()}")
        }
    }
}