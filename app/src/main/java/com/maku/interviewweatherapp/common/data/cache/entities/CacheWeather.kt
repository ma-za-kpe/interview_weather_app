package com.maku.interviewweatherapp.common.data.cache.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.maku.interviewweatherapp.common.data.cache.CacheConstants
import com.maku.interviewweatherapp.common.domain.cityWeather

@Entity(tableName = CacheConstants.CACHED_WEATHER_TABLE)
data class CacheWeather(
    @PrimaryKey(autoGenerate = false)
    val weatherId: Int,
    val all: Int?,
    val lat: Double?,
    val lon: Double?,
    val dt: Int?,
    val feelsLike: Double?,
    val grndLevel: Int?,
    val humidity: Int?,
    val pressure: Int?,
    val seaLevel: Int?,
    val temp: Double?,
    val tempMax: Double?,
    val tempMin: Double?,
    val country: String?,
    val sunrise: Int?,
    val sunset: Int?,
    val timezone: Int?,
    val weather: String,
    val deg: Int?,
    val speed: Double?
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0

    companion object {
        fun fromDomain(domainModel: cityWeather): CacheWeather {
            val clouds = domainModel.clouds
            val coord = domainModel.coord
            val main = domainModel.main
            val sys = domainModel.sys
            val wind = domainModel.wind

            return CacheWeather(
                weatherId = domainModel.id,
                all = clouds.all.toString().toInt(),
                lat = coord.lat.toString().toDouble(),
                lon = coord.lon.toString().toDouble(),
                dt = domainModel.dt,
                feelsLike = main.feelsLike.toString().toDouble(),
                grndLevel = main.grndLevel.toString().toInt(),
                humidity = main.humidity.toString().toInt(),
                pressure = main.pressure.toString().toInt(),
                seaLevel = main.seaLevel.toString().toInt(),
                temp = main.temp.toString().toDouble(),
                tempMax = main.tempMax.toString().toDouble(),
                tempMin = main.tempMin.toString().toDouble(),
                country = sys.country.toString(),
                sunrise = sys.sunrise.toString().toInt(),
                sunset = sys.sunset.toString().toInt(),
                timezone = sys.timezone.toString().toInt(),
                weather = domainModel.weather.toString(),
                deg = wind.deg.toString().toInt(),
                speed = wind.speed.toString().toDouble()
            )
        }
    }
}
