package com.example.weatherly.data.repository

import com.example.weatherly.data.dto.CurrentWeather
import com.example.weatherly.data.remote.WeatherService
import com.example.weatherly.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class WeatherRepositoryImpl (
    private val weatherService: WeatherService
): WeatherRepository {
    override suspend fun getCurrentWeather(city: String): Flow<CurrentWeather> {
        return flow {
            val response = weatherService.getCurrentWeather(city)
            emit(response)
        }.catch {
            TODO()
        }
    }
}