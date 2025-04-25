package com.example.weatherly.presentation.navigation

import com.example.weatherly.presentation.utils.Constant.CURRENT_WEATHER
import com.example.weatherly.presentation.utils.Constant.FORECAST_WEATHER

sealed class Route(val route: String) {
    data object CurrentWeather : Route(route = CURRENT_WEATHER)
    data object ForecastWeather : Route(route = FORECAST_WEATHER)
}