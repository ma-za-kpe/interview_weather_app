package com.maku.interviewweatherapp.common.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maku.interviewweatherapp.common.data.api.models.CityWeather
import com.maku.interviewweatherapp.common.data.cache.entities.CacheWeather

class SharedViewModel : ViewModel() {
    var allSelectedWeather = MutableLiveData<CacheWeather>()

    fun selectWeather(item: CacheWeather) {
        allSelectedWeather.value = item
    }
}