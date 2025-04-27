package com.example.weatherly.presentation.screen.ForecastWeather.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherly.R
import com.example.weatherly.presentation.common.BaseText
import com.example.weatherly.presentation.common.Divider
import com.example.weatherly.presentation.common.ForecastTopAppBar
import com.example.weatherly.presentation.common.ForecastWeatherCard
import com.example.weatherly.presentation.common.SpacerHeight
import com.example.weatherly.presentation.common.WeatherIcon
import com.example.weatherly.presentation.common.WeatherImage
import com.example.weatherly.presentation.screen.ForecastWeather.viewmodel.ForecastWeatherViewModel
import com.example.weatherly.presentation.screen.ForecastWeather.state.ForecastWeatherIntent
import com.example.weatherly.presentation.screen.ForecastWeather.state.ForecastWeatherState
import com.example.weatherly.presentation.utils.Dimen
import com.example.weatherly.presentation.utils.Dimen.SmallSpace
import com.example.weatherly.ui.theme.PrimaryColor
import com.example.weatherly.ui.theme.SecondaryColor

@Composable
fun ForecastWeatherScreen (
    city: String,
    navigateUp: () -> Unit
) {
    val forecastWeatherViewModel: ForecastWeatherViewModel = hiltViewModel()
    val forecastWeatherState by forecastWeatherViewModel.forecastWeatherState

    LaunchedEffect(Unit) {
        forecastWeatherViewModel.onIntent(
            ForecastWeatherIntent.FetchForecastWeather(city = city)
        )
    }

    ForecastWeatherScreenContent(
        forecastWeatherState = forecastWeatherState,
        navigateUp = navigateUp
    )
}

@Composable
fun ForecastWeatherScreenContent (
    forecastWeatherState: ForecastWeatherState,
    navigateUp: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryColor)
            .padding(SmallSpace)
    ) {
        val current = forecastWeatherState.forecastWeather.current
        val location = forecastWeatherState.forecastWeather.location
        val expandedDay = remember { mutableStateOf<String?>(null) }

        ForecastTopAppBar(
            title = "7-Day Forecast",
            navigateUp = { navigateUp() }
        )
        SpacerHeight(Dimen.MediumSpace)
        BaseText(
            text = "${location.name}, ${location.country}",
            fontSize = 24,
        )
        BaseText(
            text = location.region,
            fontWeight = FontWeight.Normal
        )
        WeatherIcon(condition = current.condition)
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BaseText(
                text = "${current.temperatureC.toInt()} °C",
                fontSize = 48,
            )
            Column {
                BaseText(location.localtime.take(10))
                BaseText(location.localtime.takeLast(5))
            }
        }
        SpacerHeight(SmallSpace)
        Divider()
        SpacerHeight(SmallSpace)
        BaseText("Current Weather: ${current.condition.text}")
        SpacerHeight(SmallSpace)
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    shape = RoundedCornerShape(SmallSpace),
                    color = SecondaryColor
                ).padding(Dimen.ExtraSmallSpace),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            WeatherImage(R.drawable.wind)
            Column {
                BaseText("Wind")
                BaseText(
                    text = "${current.windKph.toInt()} km/h",
                    fontSize = 24,
                    color = PrimaryColor
                )
            }
            WeatherImage(R.drawable.pressure)
            Column {
                BaseText("Pressure")
                BaseText(
                    text = "${current.pressureMb.toInt()} mb",
                    fontSize = 24,
                    color = PrimaryColor
                )
            }
        }
        SpacerHeight(Dimen.LargeSpace)
        Divider()
        SpacerHeight(Dimen.MediumSpace)
        BaseText(
            text = "Forecast",
            fontSize = 24,
            fontWeight = FontWeight.Bold
        )
        SpacerHeight(Dimen.MediumSpace)
        LazyColumn (
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(Dimen.UnderMediumSpace)
        ) {
            items (forecastWeatherState.forecastWeather.forecast.forecastDay) { day ->
                ForecastWeatherCard(
                    forecastDay = day,
                    isExpanded = expandedDay.value == day.date,
                    onCardClick = {
                        expandedDay.value = day.date.takeIf { expandedDay.value != day.date }
                    }
                )
            }
        }
    }
}