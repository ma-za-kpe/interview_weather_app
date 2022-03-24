package com.maku.interviewweatherapp.list.home

import android.app.Application
import androidx.lifecycle.*
import com.maku.interviewweatherapp.common.data.api.ApiConstants
import com.maku.interviewweatherapp.common.data.api.models.WeatherResponse
import com.maku.interviewweatherapp.common.data.cache.entities.CacheWeather
import com.maku.interviewweatherapp.common.data.cache.entities.FavoriteWeatherEntity
import com.maku.interviewweatherapp.common.data.repo.WeatherRepoImpl
import com.maku.interviewweatherapp.common.utils.NetworkException
import com.maku.interviewweatherapp.common.utils.NetworkResult
import com.maku.interviewweatherapp.common.utils.NetworkUnavailableException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (private val repo: WeatherRepoImpl, application: Application):
    AndroidViewModel(application) {

    /**ROOM*/
    // fav local weather
    val readAllFavWeatherData: LiveData<List<FavoriteWeatherEntity>> = repo.getFavWeather().asLiveData()

    fun insertFavWeatherData(weatherEntity: FavoriteWeatherEntity) = viewModelScope.launch(
        Dispatchers.IO){
        repo.storeFavWeather(weatherEntity)
    }

    // local weather
    val readAllWeatherData: LiveData<List<CacheWeather>> = repo.getWeather().asLiveData()

    private fun insertWeatherData(weatherEntity: CacheWeather) = viewModelScope.launch(
        Dispatchers.IO){
        repo.storeWeather(weatherEntity)
    }
    /**ROOM*/

    // get all weather data
    var weatherResponse: MutableLiveData<NetworkResult<WeatherResponse>> = MutableLiveData()
    // pass the current user id here
    fun getWeatherData() = viewModelScope.launch {
        weatherResponse.value = NetworkResult.Loading()

            try {
                val response = repo.requestWeatherData("235039,184745,232422,4164143,4348460,6544881,2161311," +
                        "161325,186301,184622,2634894,5124925,5161693,971421,5144089,360630,3369157,524901," +
                        "703448,2643743", ApiConstants.APP_ID)
                weatherResponse.value = handleGetAllWeatherResponse(response)
                // cache data immediately after receiving it
                val weather = weatherResponse.value!!.data
                if (weather != null){
                    //cache
                    offlineCacheWeather(weather)
                }
            } catch (e: Exception) {
                when (e) {
                    is NetworkException,
                    is NetworkUnavailableException -> {
                        weatherResponse.value = NetworkResult.Error(e.toString())
                    }
                    else -> {
                        weatherResponse.value = NetworkResult.Error(e.toString())
                    }
                }
            }
    }

    private fun offlineCacheWeather(weather: WeatherResponse?) {
        val weatherEntity = weather.list.forEach { CacheWeather(it.id) }
        insertWeatherData(weatherEntity)
    }

    // TODO: replace or add with more error codes from the api docs
    // DO WE EVEN NEED THIS?
    private fun handleGetAllWeatherResponse(response: Response<WeatherResponse>): NetworkResult<WeatherResponse> {
        return when {
            response.code() == 500 -> {
                // check this for more details on returns
                NetworkResult.Error(response.raw().message)
            }

            response.code() == 400 -> {
                NetworkResult.Error(response.raw().message)
            }

            response.code() == 401 -> {
                NetworkResult.Error(response.raw().message)
            }

            response.isSuccessful -> {
                val weatherResponse = response.body()
                NetworkResult.Success(weatherResponse!!)
            }
            else -> {
                NetworkResult.Error(response.raw().message)
            }
        }
    }
}