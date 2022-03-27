package com.maku.interviewweatherapp.common.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maku.interviewweatherapp.common.data.api.models.CityWeather
import com.maku.interviewweatherapp.common.data.cache.entities.WeatherEntity

class SharedViewModel : ViewModel() {
    var allSelectedWeather = MutableLiveData<CityWeather>()

    fun selectWeather(item: CityWeather) {
        allSelectedWeather.value = item
    }
}