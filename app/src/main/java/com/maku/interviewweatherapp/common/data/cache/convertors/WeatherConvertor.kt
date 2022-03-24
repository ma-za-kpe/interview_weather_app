package com.maku.interviewweatherapp.common.data.cache.convertors

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.maku.interviewweatherapp.common.data.api.models.CityWeather

class WeatherConvertor {
    var gson = Gson()

    @TypeConverter
    fun weatherToString(weatherResponse: List<CityWeather>): String{
        return gson.toJson(weatherResponse)
    }
    @TypeConverter
    fun stringToWeather(data: String): List<CityWeather>{
        val listType = object: TypeToken<List<CityWeather>>(){}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun cityWeatherToString(weatherResponse: CityWeather): String{
        return gson.toJson(weatherResponse)
    }
    @TypeConverter
    fun stringToCityWeather(data: String): CityWeather{
        val listType = object: TypeToken<CityWeather>(){}.type
        return gson.fromJson(data, listType)
    }
}
