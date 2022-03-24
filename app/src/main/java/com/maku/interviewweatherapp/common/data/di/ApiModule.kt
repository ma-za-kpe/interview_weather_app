package com.maku.interviewweatherapp.common.data.di

import com.maku.interviewweatherapp.common.data.api.ApiConstants
import com.maku.interviewweatherapp.common.data.api.WeatherAPI
import com.maku.interviewweatherapp.common.data.api.interceptors.LoggingInterceptor
import com.maku.interviewweatherapp.common.data.api.interceptors.NetworkStatusInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

  @Provides
  @Singleton
  fun provideApi(builder: Retrofit.Builder): WeatherAPI {
    return builder
        .build()
        .create(WeatherAPI::class.java)
  }

  @Provides
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder {
    return Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_ENDPOINT)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
  }

  @Provides
  fun provideOkHttpClient(
      httpLoggingInterceptor: HttpLoggingInterceptor,
      networkStatusInterceptor: NetworkStatusInterceptor,
  ): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(networkStatusInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .build()
  }

  @Provides
  fun provideHttpLoggingInterceptor(loggingInterceptor: LoggingInterceptor): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor(loggingInterceptor)
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
  }

}