package com.maku.interviewweatherapp.common.data.api

object ApiConstants {
    // https://api.openweathermap.org/data/2.5/group?id=235039,184745,232422,4164143,4348460,6544881,
    // 2161311,161325,186301,184622,2634894,5124925,5161693,971421,5144089,360630,3369157,524901,703448,
    // 2643743&appid=7b5996503e9218b9c71181c479a20df0
    const val BASE_ENDPOINT = "https://api.openweathermap.org/"
    const val WEATHER_ENDPOINT = "data/2.5/group"

    //TODO: find a better way to handle keys, like keys.gradle file
    const val APP_ID = "7b5996503e9218b9c71181c479a20df0"
}

object ApiParameters {
    const val CITY_ID = "id"
    const val APP_ID = "appid"
}