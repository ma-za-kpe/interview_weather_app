package com.maku.interviewweatherapp.common.data.repo

import com.maku.interviewweatherapp.common.data.api.models.WeatherResponse
import com.maku.interviewweatherapp.common.data.cache.entities.CacheWeather
import com.maku.interviewweatherapp.common.data.cache.entities.FavoriteWeatherEntity
import com.maku.interviewweatherapp.common.data.cache.entities.WeatherEntity
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

// TODO: unecessary repeatition, tyr domain modelling instead
interface WeatherRepo {
    fun getWeather(): Flow<List<CacheWeather>>
    suspend fun storeWeather(weather: CacheWeather)
    suspend fun requestWeatherData(idString: String, apikey: String): Response<WeatherResponse>

    suspend fun storeFavWeather(weather: FavoriteWeatherEntity)
    fun getFavWeather(): Flow<List<FavoriteWeatherEntity>>
}