package com.maku.interviewweatherapp.common.data.repo

import com.maku.interviewweatherapp.common.data.api.WeatherAPI
import com.maku.interviewweatherapp.common.data.api.models.WeatherResponse
import com.maku.interviewweatherapp.common.data.cache.Cache
import com.maku.interviewweatherapp.common.data.cache.entities.CacheWeather
import com.maku.interviewweatherapp.common.data.cache.entities.FavoriteWeatherEntity
import com.maku.interviewweatherapp.common.data.cache.entities.WeatherEntity
import com.maku.interviewweatherapp.common.utils.NetworkException
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class WeatherRepoImpl @Inject constructor(
    private val api: WeatherAPI, // remote
    private val cache: Cache, // local
)  : WeatherRepo {
    override fun getWeather(): Flow<List<CacheWeather>> {
        return cache.getWeather()
    }

    override fun getAnimals(): Flow<List<CacheWeather>> {
        return cache.getWeather()
            .distinctUntilChanged()
            .map { animalList ->
                animalList.map { it.animal.toAnimalDomain(it.photos, it.videos, it.tags) }
            }
    }


    override suspend fun storeWeather(weather: CacheWeather) {
        cache.storeWeather(weather.ma)
        cache.storeNearbyAnimals(animals.map { CachedAnimalAggregate.fromDomain(it) })
    }

    // city list here: http://web.archive.org/web/20180619015316/http://openweathermap.org/help/city_list.txt
    override suspend fun requestWeatherData(idString: String, api_key: String): Response<WeatherResponse> {
        try {
            return api.getWeatherFromCities(idString, api_key)
        } catch (exception: HttpException) {
            throw NetworkException(exception.message ?: "Code ${exception.code()}")
        }
    }

    override suspend fun storeFavWeather(weather: FavoriteWeatherEntity) {
        cache.storeFavWeather(weather)
    }

    override fun getFavWeather(): Flow<List<FavoriteWeatherEntity>> {
       return cache.getFavWeather()
    }
}