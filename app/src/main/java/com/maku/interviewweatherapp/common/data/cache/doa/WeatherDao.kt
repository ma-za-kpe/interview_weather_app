package com.maku.interviewweatherapp.common.data.cache.doa

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maku.interviewweatherapp.common.data.cache.entities.CacheWeather
import com.maku.interviewweatherapp.common.data.cache.entities.FavoriteWeatherEntity
import com.maku.interviewweatherapp.common.data.cache.entities.WeatherEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
//    // weather
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertWeather(weatherEntity: WeatherEntity)
//
//    @Query("SELECT * FROM city_weather ORDER BY id ASC")
//    fun readWeather(): Flow<List<WeatherEntity>>

    // cached weather
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weatherEntity: CacheWeather)

    @Query("SELECT * FROM cached_city_weather ORDER BY id ASC")
    fun readWeather(): Flow<List<CacheWeather>>

    // fav weather
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavWeather(weatherEntity: FavoriteWeatherEntity)

    @Query("SELECT * FROM fav_city_weather ORDER BY id ASC")
    fun readFavWeather(): Flow<List<FavoriteWeatherEntity>>
}