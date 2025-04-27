package com.example.weatherly.data.remote

import com.example.weatherly.data.dto.CurrentWeather
import com.example.weatherly.data.dto.ForecastWeather
import com.example.weatherly.utils.Constant
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("key") apiKey: String = Constant.API_KEY
    ): CurrentWeather

    @GET("forecast.json")
    suspend fun getForecastWeather(
        @Query("q") city: String,
        @Query("days") days: Int = 10,
        @Query("key") apiKey: String = Constant.API_KEY,
    ): ForecastWeather
}