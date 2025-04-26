package com.example.weatherly.data.remote

import com.example.weatherly.data.dto.CurrentWeather
import com.example.weatherly.data.dto.ForecastWeather
import com.example.weatherly.data.utils.DataConstant
import com.example.weatherly.di.Constant
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String = Constant.API_KEY,
        @Query("units") units: String = DataConstant.METRIC
    ): CurrentWeather

    @GET("data/2.5/forecast")
    suspend fun getForecastWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String = Constant.API_KEY,
        @Query("units") units: String = DataConstant.METRIC
    ): ForecastWeather
}