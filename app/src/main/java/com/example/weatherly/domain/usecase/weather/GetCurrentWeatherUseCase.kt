package com.example.weatherly.domain.usecase.weather

import com.example.weatherly.data.dto.CurrentWeather
import com.example.weatherly.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class GetCurrentWeatherUseCase (
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(city: String): Flow<CurrentWeather> {
        return weatherRepository.getCurrentWeather(city)
    }
}