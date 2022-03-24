package com.maku.interviewweatherapp.common.data.api.models.mapper

import com.maku.interviewweatherapp.common.data.api.models.Weather
import com.maku.interviewweatherapp.common.domain.clouds
import com.maku.interviewweatherapp.common.domain.weather
import javax.inject.Inject

class ApiWeatherMapper @Inject constructor() : ApiMapper<Weather?, weather> {

  override fun mapToDomain(apiEntity: Weather?): weather {
      return weather(
          description = apiEntity?.description,
          icon = apiEntity?.icon,
          id = apiEntity?.id,
          main = apiEntity?.main
      )
  }
}