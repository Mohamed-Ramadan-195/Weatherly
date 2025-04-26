package com.example.weatherly.data.dto

data class ForecastWeather(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Item0>,
    val message: Int
)

data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
)

data class Item0(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: ForecastMain,
    val pop: Double,
    val rain: Rain,
    val sys: ForecastSys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: ForecastWind
)

data class ForecastMain(
    val feels_like: Double,
    val grnd_level: Int,
    val humidity: Int,
    val pressure: Int,
    val sea_level: Int,
    val temp: Double,
    val temp_kf: Double,
    val temp_max: Double,
    val temp_min: Double
)

data class Rain(
    val `3h`: Double
)

data class ForecastSys(
    val pod: String
)

data class ForecastWind(
    val deg: Int,
    val gust: Double,
    val speed: Double
)