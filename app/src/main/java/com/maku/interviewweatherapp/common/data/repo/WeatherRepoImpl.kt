package com.maku.interviewweatherapp.common.data.repo

import com.maku.interviewweatherapp.common.data.api.WeatherAPI
import com.maku.interviewweatherapp.common.data.api.models.CityWeather
import com.maku.interviewweatherapp.common.data.api.models.WeatherResponse
import com.maku.interviewweatherapp.common.data.cache.Cache
import com.maku.interviewweatherapp.common.utils.NetworkException
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class  WeatherRepoImpl @Inject constructor(
    private val api: WeatherAPI, // remote
    private val cache: Cache, // local
)  : WeatherRepo {
    override fun getWeather(): Flow<List<CityWeather>> {
        return cache.getWeather()
    }

    override suspend fun storeWeather(weather: CityWeather) {
        cache.storeWeather(weather)
    }

    override suspend fun favWeather(fav: Boolean, id: Int ) {
        cache.favWeather(fav, id)
    }

    // city list here: http://web.archive.org/web/20180619015316/http://openweathermap.org/help/city_list.txt
    override suspend fun requestWeatherData(idString: String, api_key: String): Response<WeatherResponse> {
        try {
            return api.getWeatherFromCities(idString, api_key)
        } catch (exception: HttpException) {
            throw NetworkException(exception.message ?: "Code ${exception.code()}")
        }
    }
}