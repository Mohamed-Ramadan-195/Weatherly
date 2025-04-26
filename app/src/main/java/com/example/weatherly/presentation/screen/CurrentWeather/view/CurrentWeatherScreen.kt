package com.example.weatherly.presentation.screen.CurrentWeather.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherly.presentation.common.CityTextField
import com.example.weatherly.presentation.common.SpacerHeight
import com.example.weatherly.presentation.screen.CurrentWeather.state.CurrentWeatherEvent
import com.example.weatherly.presentation.screen.CurrentWeather.state.CurrentWeatherState
import com.example.weatherly.presentation.screen.CurrentWeather.viewmodel.CurrentWeatherViewModel
import com.example.weatherly.presentation.utils.Dimen
import com.example.weatherly.ui.theme.PrimaryColor

@Composable
fun CurrentWeatherScreen (
    navigateToForecast: (Double, Double) -> Unit
) {
    val currentWeatherViewModel: CurrentWeatherViewModel = hiltViewModel()
    val currentWeatherState = currentWeatherViewModel.currentWeatherState.value

    CurrentWeatherScreenContent(
        navigateToForecast = navigateToForecast,
        currentWeatherState = currentWeatherState,
        currentWeatherEvent = currentWeatherViewModel::onEvent,
    )
}

@Composable
fun CurrentWeatherScreenContent(
    navigateToForecast: (Double, Double) -> Unit,
    currentWeatherState: CurrentWeatherState,
    currentWeatherEvent: (CurrentWeatherEvent) -> Unit,
) {
   val weather = currentWeatherState.currentWeather
   Column (
       modifier = Modifier
           .fillMaxSize()
           .background(PrimaryColor)
           .padding(Dimen.MediumSpace),
       horizontalAlignment = Alignment.CenterHorizontally
   ) {
       CityTextField(
          text = currentWeatherState.city,
          readOnly = false,
          onValueChange = { currentWeatherEvent(CurrentWeatherEvent.UpdateCity(it)) },
          onSearch = { currentWeatherEvent(CurrentWeatherEvent.CityWeather) }
       )
       SpacerHeight(Dimen.MediumSpace)
       Column (
           modifier = Modifier.fillMaxWidth(),
           horizontalAlignment = Alignment.CenterHorizontally
       ) {
           Text (
               text = weather.name,
               fontSize = 24.sp,
               fontWeight = FontWeight.Bold,
               fontFamily = FontFamily.Serif,
               color = Color.White
           )
           SpacerHeight(Dimen.SmallSpace)
           Text (
               text = "${weather.main.temperature} °C",
               fontSize = 48.sp,
               fontWeight = FontWeight.Bold,
               fontFamily = FontFamily.Serif,
               color = Color.White
           )
       }
   }
}