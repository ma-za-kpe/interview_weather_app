package com.maku.interviewweatherapp.common.data.cache.doa

import androidx.room.*
import com.maku.interviewweatherapp.common.data.api.models.CityWeather
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    // weather
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weatherEntity: CityWeather)

    @Query("SELECT * FROM city_weather ORDER BY fav DESC")
    fun readWeather(): Flow<List<CityWeather>>

    /**
     * Updating only fav
     */
    @Query("UPDATE city_weather SET fav=:fav WHERE id = :id")
    fun favWeather(fav: Boolean?, id: Int)

}