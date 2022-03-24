package com.maku.interviewweatherapp.common.data.api.models.mapper

import com.maku.interviewweatherapp.common.data.api.models.Clouds
import com.maku.interviewweatherapp.common.data.api.models.Weather
import com.maku.interviewweatherapp.common.domain.clouds
import javax.inject.Inject

class ApiCloudMapper @Inject constructor() : ApiMapper<Clouds?, clouds> {

  override fun mapToDomain(apiEntity: Clouds?): clouds {
    return clouds(
        all = apiEntity?.all
    )
  }
}