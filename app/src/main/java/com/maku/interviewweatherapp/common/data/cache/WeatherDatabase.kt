package com.maku.interviewweatherapp.common.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.maku.interviewweatherapp.common.data.cache.convertors.WeatherConvertor
import com.maku.interviewweatherapp.common.data.cache.doa.WeatherDao
import com.maku.interviewweatherapp.common.data.cache.entities.WeatherEntity

@Database(
    entities = [
      WeatherEntity::class
    ],
    version = 1
)
@TypeConverters(WeatherConvertor::class)
abstract class WeatherDatabase : RoomDatabase() {
  abstract fun weatherDao(): WeatherDao
}