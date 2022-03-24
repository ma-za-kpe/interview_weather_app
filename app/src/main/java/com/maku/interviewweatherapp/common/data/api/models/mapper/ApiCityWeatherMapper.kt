package com.maku.interviewweatherapp.common.data.api.models.mapper

import com.maku.interviewweatherapp.common.data.api.models.CityWeather
import com.maku.interviewweatherapp.common.data.api.models.Weather
import com.maku.interviewweatherapp.common.domain.cityWeather
import com.maku.interviewweatherapp.common.domain.weather
import com.maku.interviewweatherapp.common.utils.MappingException
import javax.inject.Inject

class ApiCityWeatherMapper @Inject constructor(
    private val cloudMapper: ApiCloudMapper,
    private val coordsMapper: ApiCoordsMapper,
    private val mainMapper: ApiMainMapper,
    private val sysMapper: ApiSysMapper,
    private val windMapper: ApiWindMapper
): ApiMapper<CityWeather?, cityWeather>{
    override fun mapToDomain(apiEntity: CityWeather?): cityWeather {
        return cityWeather(
            clouds = cloudMapper.mapToDomain(apiEntity?.clouds),
            coord = coordsMapper.mapToDomain(apiEntity?.coord),
            dt = apiEntity?.dt,
            id = apiEntity?.id ?: throw MappingException("Weather ID cannot be null"),
            main = mainMapper.mapToDomain(apiEntity.main),
            name = apiEntity.name,
            sys = sysMapper.mapToDomain(apiEntity.sys),
            visibility  = apiEntity.visibility,
            weather = apiEntity.weather.toString(),
            wind = windMapper.mapToDomain(apiEntity.wind),
            favorite = false
        )
    }

}
