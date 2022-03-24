package com.maku.interviewweatherapp.common.data.api

import com.maku.interviewweatherapp.common.data.api.models.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET(ApiConstants.WEATHER_ENDPOINT)
    suspend fun getWeatherFromCities(
        @Query(ApiParameters.CITY_ID) id: String,
        @Query(ApiParameters.APP_ID) app_id: String
    ): Response<WeatherResponse>
}