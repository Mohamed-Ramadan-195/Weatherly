package com.example.weatherly.domain.usecase.weather

import com.example.weatherly.data.dto.Condition
import com.example.weatherly.data.dto.Current
import com.example.weatherly.data.dto.CurrentWeather
import com.example.weatherly.data.dto.Location

fun createCurrentWeather (
    city: String
): CurrentWeather {
   return CurrentWeather(
        current = Current(
            cloud = 0,
            condition = Condition(0,"", ""),
            dewpointC = 0.0,
            dewpointF = 0.0,
            feelsLikeC = 0.0,
            feelsLikeF = 0.0,
            gustKph = 0.0,
            gustMph = 0.0,
            heatindexC = 0.0,
            heatindexF = 0.0,
            humidity = 0,
            isDay = 0,
            lastUpdated = "",
            lastUpdatedEpoch = 0,
            precipIn = 0.0,
            precipMm = 0.0,
            pressureIn = 0.0,
            pressureMb = 0.0,
            temperatureC = 0.0,
            temperatureF = 0.0,
            uv = 0.0,
            visKm = 0.0,
            visMiles = 0.0,
            windDegree = 1,
            windDir = "",
            windKph = 0.0,
            windMph = 0.0,
            windchillC = 0.0,
            windchillF = 0.0
        ),
        location = Location(
            name = city,
            region = "",
            country = "",
            lat = 0.0,
            lon = 0.0,
            tzId = "",
            localtimeEpoch = 0,
            localtime = ""
        )
    )
}