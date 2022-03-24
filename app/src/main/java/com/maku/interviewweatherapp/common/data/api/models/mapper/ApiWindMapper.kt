package com.maku.interviewweatherapp.common.data.api.models.mapper

import com.maku.interviewweatherapp.common.data.api.models.Clouds
import com.maku.interviewweatherapp.common.data.api.models.Weather
import com.maku.interviewweatherapp.common.data.api.models.Wind
import com.maku.interviewweatherapp.common.domain.clouds
import com.maku.interviewweatherapp.common.domain.wind
import javax.inject.Inject

class ApiWindMapper @Inject constructor() : ApiMapper<Wind?, wind> {

  override fun mapToDomain(apiEntity: Wind?): wind {
    return wind(
        deg = apiEntity?.deg,
        speed = apiEntity?.speed
    )
  }
}