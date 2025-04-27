package com.example.weatherly.presentation.screen.CurrentWeather.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherly.R
import com.example.weatherly.presentation.common.BaseText
import com.example.weatherly.presentation.common.CityTextField
import com.example.weatherly.presentation.common.Divider
import com.example.weatherly.presentation.common.SpacerHeight
import com.example.weatherly.presentation.common.WeatherIcon
import com.example.weatherly.presentation.common.WeatherImage
import com.example.weatherly.presentation.screen.CurrentWeather.state.CurrentWeatherEvent
import com.example.weatherly.presentation.screen.CurrentWeather.state.CurrentWeatherState
import com.example.weatherly.presentation.screen.CurrentWeather.viewmodel.CurrentWeatherViewModel
import com.example.weatherly.presentation.utils.Dimen
import com.example.weatherly.ui.theme.PrimaryColor
import com.example.weatherly.ui.theme.SecondaryColor

@Composable
fun CurrentWeatherScreen (
    navigateToForecast: (String) -> Unit
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
    navigateToForecast: (String) -> Unit,
    currentWeatherState: CurrentWeatherState,
    currentWeatherEvent: (CurrentWeatherEvent) -> Unit,
) {
   Column (
       modifier = Modifier
           .fillMaxSize()
           .background(PrimaryColor)
           .padding(Dimen.MediumSpace)
           .verticalScroll(rememberScrollState()),
   ) {
       val current = currentWeatherState.currentWeather
       CityTextField (
          modifier = Modifier.fillMaxWidth(),
          text = currentWeatherState.city,
          readOnly = false,
          onValueChange = { currentWeatherEvent(CurrentWeatherEvent.UpdateCity(it)) },
          onSearch = { currentWeatherEvent(CurrentWeatherEvent.CityWeather) }
       )
       SpacerHeight(Dimen.MediumSpace)
       if (currentWeatherState.city.isNotEmpty()) {
           BaseText(
               text = "${current.location.name}, ${current.location.country}",
               fontSize = 24
           )
           BaseText(
               text = current.location.region,
               fontWeight = FontWeight.Normal
           )
           WeatherIcon(condition = current.current.condition)
           Row (
               modifier = Modifier.fillMaxWidth(),
               horizontalArrangement = Arrangement.SpaceBetween,
               verticalAlignment = Alignment.CenterVertically,
           ) {
               BaseText(
                   text = "${current.current.temperatureC.toInt()} °C",
                   fontSize = 48,
               )
               Column {
                   BaseText(current.location.localtime.take(10))
                   BaseText(current.location.localtime.takeLast(5))
               }
           }
           SpacerHeight(Dimen.SmallSpace)
           Divider()
           SpacerHeight(Dimen.SmallSpace)
           BaseText("Current Weather: ${current.current.condition.text}")
           SpacerHeight(Dimen.SmallSpace)
           Row (
               modifier = Modifier
                   .fillMaxWidth()
                   .background(
                       shape = RoundedCornerShape(Dimen.SmallSpace),
                       color = SecondaryColor
                   ).padding(Dimen.ExtraSmallSpace),
               horizontalArrangement = Arrangement.SpaceEvenly,
               verticalAlignment = Alignment.CenterVertically
           ) {
               WeatherImage(R.drawable.wind)
               Column {
                   BaseText("Wind")
                   BaseText(
                       text = "${current.current.windKph.toInt()} km/h",
                       fontSize = 24,
                       color = PrimaryColor
                   )
               }
               WeatherImage(R.drawable.pressure)
               Column {
                   BaseText("Pressure")
                   BaseText(
                       text = "${current.current.pressureMb.toInt()} mb",
                       fontSize = 24,
                       color = PrimaryColor
                   )
               }
           }
           SpacerHeight(Dimen.LargeSpace)
           IconButton (
               modifier = Modifier.clip(CircleShape).fillMaxWidth(),
               onClick = {
                   navigateToForecast(current.location.name)
               }
           ) {
               Image (
                   painter = painterResource(R.drawable.right_arrow),
                   contentDescription = "Forecast"
               )
           }
           Text(
               modifier = Modifier.align(Alignment.CenterHorizontally),
               text = "Go to Forecast",
               fontSize = 20.sp,
               fontWeight = FontWeight.SemiBold,
               fontFamily = FontFamily.SansSerif,
               color = Color.White
           )
       }
       SpacerHeight(Dimen.LargeSpace)
   }
}