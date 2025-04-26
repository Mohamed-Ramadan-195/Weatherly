package com.example.weatherly.presentation.screen.CurrentWeather.state

sealed class CurrentWeatherEvent {

    data class UpdateCity(
        val city: String
    ): CurrentWeatherEvent()

    data object CityWeather: CurrentWeatherEvent()
}