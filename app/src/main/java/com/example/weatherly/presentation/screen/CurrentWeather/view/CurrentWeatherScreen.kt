package com.example.weatherly.presentation.screen.CurrentWeather.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherly.presentation.common.CityTextField
import com.example.weatherly.presentation.common.InfoText
import com.example.weatherly.presentation.common.SpacerHeight
import com.example.weatherly.presentation.screen.CurrentWeather.state.CurrentWeatherEvent
import com.example.weatherly.presentation.screen.CurrentWeather.state.CurrentWeatherState
import com.example.weatherly.presentation.screen.CurrentWeather.viewmodel.CurrentWeatherViewModel
import com.example.weatherly.presentation.utils.Dimen
import com.example.weatherly.ui.theme.PrimaryColor
import com.example.weatherly.ui.theme.SecondaryColor

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
       Text (
            text = "Weatherly",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontSize = 24.sp,
            color = Color.White,
       )
       SpacerHeight(Dimen.MediumSpace)
       CityTextField (
            text = currentWeatherState.city,
            readOnly = false,
            onValueChange = { currentWeatherEvent(CurrentWeatherEvent.UpdateCity(it)) },
            onSearch = { currentWeatherEvent(CurrentWeatherEvent.CityWeather) },
       )
       SpacerHeight(Dimen.MediumSpace)
       if (weather.id != 0) {
           Box (
               modifier = Modifier
                   .fillMaxWidth()
                   .background(
                       shape = RoundedCornerShape(Dimen.SmallSpace),
                       color = SecondaryColor
                   )
           ) {
               Column (
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(Dimen.ExtraSmallSpace)
               ) {
                   InfoText (
                       address = "temperature",
                       info = "${weather.main.temperature} °C",
                   )
                   SpacerHeight(Dimen.ExtraSmallSpace)
                   InfoText (
                       address = "Wind Speed",
                       info = "${weather.wind.speed} m/s",
                   )
                   SpacerHeight(Dimen.ExtraSmallSpace)
                   InfoText (
                          address = "Wind Degree",
                          info = "${weather.wind.degrees}°",
                   )
                   SpacerHeight(Dimen.ExtraSmallSpace)
                   InfoText (
                       address = "Pressure",
                       info = "${weather.main.pressure} hPa",
                   )
               }
           }
           SpacerHeight(Dimen.MediumSpace)
       }
       Button (
           onClick = {
               navigateToForecast(
                   weather.coord.latitude,
                   weather.coord.longitude
               )
           }
       ) {
          Text (
            text = "Forecast",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.White,
          )
       }
   }
}