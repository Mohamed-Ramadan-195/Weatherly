package com.example.weatherly.presentation.screen.CurrentWeather

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CurrentWeatherScreen (

) {
    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

    }
}

@Composable
@Preview(showBackground = true)
fun CurrentWeatherScreenPreview() {
    CurrentWeatherScreen()
}