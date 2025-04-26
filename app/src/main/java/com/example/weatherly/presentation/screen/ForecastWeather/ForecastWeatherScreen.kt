package com.example.weatherly.presentation.screen.ForecastWeather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherly.presentation.utils.Dimen
import com.example.weatherly.ui.theme.PrimaryColor

@Composable
fun ForecastWeatherScreen (
    latitude: Double,
    longitude: Double
) {
    val forecastWeatherViewModel: ForecastWeatherViewModel = hiltViewModel()
    val forecastWeatherState by forecastWeatherViewModel.forecastWeatherState

    LaunchedEffect(Unit) {
        forecastWeatherViewModel.onIntent(
            ForecastWeatherIntent.FetchForecastWeather(
                latitude = latitude,
                longitude = longitude
            )
        )
    }

    ForecastWeatherScreenContent(
        forecastWeatherState = forecastWeatherState
    )
}

@Composable
fun ForecastWeatherScreenContent (
    forecastWeatherState: ForecastWeatherState
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryColor)
            .padding(all = Dimen.SmallSpace)
    ) {
        Text (
            text = forecastWeatherState.forecastWeather?.city?.name?: "No City",
        )
    }
}