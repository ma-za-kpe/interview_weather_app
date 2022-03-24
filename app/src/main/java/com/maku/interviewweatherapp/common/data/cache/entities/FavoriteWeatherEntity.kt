package com.maku.interviewweatherapp.common.data.cache.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.maku.interviewweatherapp.common.data.api.models.CityWeather
import com.maku.interviewweatherapp.common.data.cache.CacheConstants

@Entity(tableName = CacheConstants.FAV_WEATHER_TABLE)
class FavoriteWeatherEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var favWeatherCity: CityWeather
)