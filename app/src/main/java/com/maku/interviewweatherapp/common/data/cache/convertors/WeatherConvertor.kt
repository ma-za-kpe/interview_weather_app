package com.maku.interviewweatherapp.common.data.cache.convertors

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.maku.interviewweatherapp.common.data.api.models.*

class WeatherConvertor {
    var gson = Gson()

    @TypeConverter
    fun weatherToString(weatherResponse: List<Weather>): String{
        return gson.toJson(weatherResponse)
    }
    @TypeConverter
    fun stringToWeather(data: String): List<Weather>{
        val listType = object: TypeToken<List<Weather>>(){}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun windToString(weatherResponse: Wind): String{
        return gson.toJson(weatherResponse)
    }
    @TypeConverter
    fun stringToWind(data: String): Wind{
        val listType = object: TypeToken<Wind>(){}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun sysToString(weatherResponse: Sys): String{
        return gson.toJson(weatherResponse)
    }
    @TypeConverter
    fun stringToSys(data: String): Sys{
        val listType = object: TypeToken<Sys>(){}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun mainToString(weatherResponse: Main): String{
        return gson.toJson(weatherResponse)
    }
    @TypeConverter
    fun stringToMain(data: String): Main{
        val listType = object: TypeToken<Main>(){}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun coordToString(weatherResponse: Coord): String{
        return gson.toJson(weatherResponse)
    }
    @TypeConverter
    fun stringToCoord(data: String): Coord{
        val listType = object: TypeToken<Coord>(){}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun cloudToString(weatherResponse: Clouds): String{
        return gson.toJson(weatherResponse)
    }
    @TypeConverter
    fun stringToCloud(data: String): Clouds{
        val listType = object: TypeToken<Clouds>(){}.type
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
