package com.example.weatherly.domain.usecase.weather

import com.example.weatherly.data.dto.ForecastWeather
import com.example.weatherly.domain.repository.WeatherRepository

class GetForecastWeatherUseCase (
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(
        latitude: Double,
        longitude: Double
    ): ForecastWeather {
        return weatherRepository.getForecastWeather(latitude, longitude)
    }
}