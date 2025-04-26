package com.example.weatherly.domain.usecase.datastore

data class CityWeatherUseCases (
    val saveCityWeather: SaveCityWeather,
    val readCityWeather: ReadCityWeather
)