package com.example.weatherly.domain.usecase.weather

import com.example.weatherly.data.dto.ForecastWeather
import com.example.weatherly.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class GetForecastWeatherUseCase (
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(city: String): Flow<ForecastWeather> {
        return weatherRepository.getForecastWeather(city)
    }
}