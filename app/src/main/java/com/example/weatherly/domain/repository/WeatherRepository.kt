package com.example.weatherly.domain.repository

import com.example.weatherly.data.dto.CurrentWeather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getCurrentWeather(city: String): Flow<CurrentWeather>
}