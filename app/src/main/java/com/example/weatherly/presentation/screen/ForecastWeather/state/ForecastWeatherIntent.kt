package com.example.weatherly.presentation.screen.ForecastWeather.state

sealed class ForecastWeatherIntent {
    data class FetchForecastWeather(
        val latitude: Double,
        val longitude: Double
    ) : ForecastWeatherIntent()
}