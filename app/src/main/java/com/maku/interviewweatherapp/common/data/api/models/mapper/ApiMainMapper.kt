package com.maku.interviewweatherapp.common.data.api.models.mapper

import com.maku.interviewweatherapp.common.data.api.models.Main
import com.maku.interviewweatherapp.common.data.api.models.Weather
import com.maku.interviewweatherapp.common.domain.main
import javax.inject.Inject

class ApiMainMapper @Inject constructor() : ApiMapper<Main?, main> {

  override fun mapToDomain(apiEntity: Main?): main {
    return main(
        feelsLike = apiEntity?.feelsLike,
        grndLevel = apiEntity?.grndLevel,
        humidity = apiEntity?.humidity,
        pressure = apiEntity?.pressure,
        seaLevel = apiEntity?.seaLevel,
        temp = apiEntity?.temp,
        tempMax = apiEntity?.tempMax,
        tempMin = apiEntity?.tempMin
    )
  }
}
