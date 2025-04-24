package com.example.weatherly.data.remote

import com.example.weatherly.data.dto.CurrentWeather
import com.example.weatherly.di.Constant
import retrofit2.http.GET

interface WeatherService {
    @GET("data/2.5/weather/{city}/{apiKey}")
    suspend fun getCurrentWeather(
        city: String,
        apiKey: String = Constant.API_KEY
    ): CurrentWeather
}