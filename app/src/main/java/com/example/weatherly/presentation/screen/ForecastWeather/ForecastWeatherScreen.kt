package com.example.weatherly.presentation.screen.ForecastWeather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.weatherly.presentation.utils.Dimen
import com.example.weatherly.ui.theme.PrimaryColor

@Composable
fun ForecastWeatherScreen (

) {
    ForecastWeatherScreenContent()
}

@Composable
fun ForecastWeatherScreenContent (

) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryColor)
            .padding(all = Dimen.SmallSpace)
    ) {

    }
}