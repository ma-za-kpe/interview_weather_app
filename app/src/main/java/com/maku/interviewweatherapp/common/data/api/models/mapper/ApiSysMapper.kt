package com.maku.interviewweatherapp.common.data.api.models.mapper

import com.maku.interviewweatherapp.common.data.api.models.Clouds
import com.maku.interviewweatherapp.common.data.api.models.Sys
import com.maku.interviewweatherapp.common.data.api.models.Weather
import com.maku.interviewweatherapp.common.domain.clouds
import com.maku.interviewweatherapp.common.domain.sys
import javax.inject.Inject

class ApiSysMapper @Inject constructor() : ApiMapper<Sys?, sys> {

  override fun mapToDomain(apiEntity: Sys?): sys {
    return sys(
        country = apiEntity?.country,
        sunrise = apiEntity?.sunrise,
        sunset = apiEntity?.sunset,
        timezone = apiEntity?.timezone
    )
  }
}