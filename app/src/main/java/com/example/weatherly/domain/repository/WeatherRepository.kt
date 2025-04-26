package com.example.weatherly.domain.repository

import com.example.weatherly.data.dto.CurrentWeather
import com.example.weatherly.data.dto.ForecastWeather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getCurrentWeather(
        city: String
    ): Flow<CurrentWeather>

//    suspend fun getForecastWeather(
//        latitude: Double,
//        longitude: Double
//    ): Flow<ForecastWeather>

    suspend fun getForecastWeather(
        latitude: Double,
        longitude: Double
    ): ForecastWeather
}