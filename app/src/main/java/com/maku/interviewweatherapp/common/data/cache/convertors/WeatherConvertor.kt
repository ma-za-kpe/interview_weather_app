package com.maku.interviewweatherapp.common.data.cache.convertors

import androidx.room.TypeConverter
import com.maku.interviewweatherapp.common.data.api.models.CityWeather
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

//class WeatherConvertor {
//    var gson = Gson()
//
//    @TypeConverter
//    fun weatherToString(weatherResponse: List<CityWeather>): String{
//        return gson.toJson(weatherResponse)
//    }
//    @TypeConverter
//    fun stringToWeather(data: String): List<CityWeather>{
//        val listType = object: TypeToken<List<CityWeather>>(){}.type
//        return gson.fromJson(data, listType)
//    }
//}

class WeatherConvertor {

    private val moshi = Moshi.Builder().build()

    @TypeConverter
    fun fromString(value: String): CityWeather? {
        val adapter: JsonAdapter<CityWeather> = moshi.adapter(CityWeather::class.java)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromInfoType(type: CityWeather): String {
        val adapter: JsonAdapter<CityWeather> = moshi.adapter(CityWeather::class.java)
        return adapter.toJson(type)
    }
}