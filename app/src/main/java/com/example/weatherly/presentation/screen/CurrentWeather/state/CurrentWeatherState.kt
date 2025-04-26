package com.example.weatherly.presentation.screen.CurrentWeather.state

import com.example.weatherly.data.dto.CurrentWeather

data class CurrentWeatherState(
    val city: String = "",
    val currentWeather: CurrentWeather = CurrentWeather.default(),
)
