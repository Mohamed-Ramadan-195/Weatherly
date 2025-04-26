package com.example.weatherly.domain.usecase.weather

data class WeatherUseCases (
    val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    val getForecastWeatherUseCase: GetForecastWeatherUseCase
)