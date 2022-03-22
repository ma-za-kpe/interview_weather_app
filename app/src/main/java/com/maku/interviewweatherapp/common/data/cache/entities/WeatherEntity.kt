package com.maku.interviewweatherapp.common.data.cache.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.maku.interviewweatherapp.common.data.api.models.CityWeather
import com.maku.interviewweatherapp.common.data.cache.CacheConstants

@Entity(tableName = CacheConstants.WEATHER_TABLE)
class WeatherEntity(
    var WeatherResponse: List<CityWeather>?
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}