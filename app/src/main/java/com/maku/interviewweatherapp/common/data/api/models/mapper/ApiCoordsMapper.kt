package com.maku.interviewweatherapp.common.data.api.models.mapper

import com.maku.interviewweatherapp.common.data.api.models.Coord
import com.maku.interviewweatherapp.common.data.api.models.Weather
import com.maku.interviewweatherapp.common.domain.coord
import javax.inject.Inject

class ApiCoordsMapper @Inject constructor() : ApiMapper<Coord?, coord> {

  override fun mapToDomain(apiEntity: Coord?): coord {
    return coord(
        lat = apiEntity?.lat,
        lon = apiEntity?.lon
    )
  }
}