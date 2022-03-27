package com.maku.interviewweatherapp.common.data.repo

import com.maku.interviewweatherapp.common.data.api.models.CityWeather
import com.maku.interviewweatherapp.common.data.api.models.WeatherResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

// TODO: unecessary repeatition, tyr domain modelling instead
interface WeatherRepo {
    fun getWeather(): Flow<List<CityWeather>>
    suspend fun storeWeather(weather: CityWeather)
    suspend fun favWeather(fav: Boolean, id: Int)

    suspend fun requestWeatherData(idString: String, apikey: String): Response<WeatherResponse>
}