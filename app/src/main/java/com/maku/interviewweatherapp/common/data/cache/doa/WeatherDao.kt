package com.maku.interviewweatherapp.common.data.cache.doa

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maku.interviewweatherapp.common.data.cache.entities.WeatherEntity
import io.reactivex.Flowable

@Dao
interface WeatherDao {

    // seasons
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weatherEntity: List<WeatherEntity>)

    @Query("SELECT * FROM city_weather ORDER BY id ASC")
    fun readWeather(): Flowable<List<WeatherEntity>>
}