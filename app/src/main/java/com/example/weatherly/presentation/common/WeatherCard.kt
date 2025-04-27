package com.example.weatherly.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherly.data.dto.Forecastday
import com.example.weatherly.data.dto.Hour
import com.example.weatherly.presentation.utils.Dimen
import com.example.weatherly.ui.theme.SecondaryColor

@Composable
fun ForecastWeatherCard (
    forecastDay: Forecastday,
    isExpanded: Boolean,
    onCardClick: () -> Unit
) {
    Card  (
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimen.UnderMediumSpace)
            .clickable { onCardClick() }
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimen.SmallSpace)
                .background(SecondaryColor),
        ) {
            WeatherIcon(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                condition = forecastDay.day.condition,
                size = 48
            )
            RowContainTextWithIcon (
                imageVector = Icons.Filled.CalendarToday,
                text = "Date: ${forecastDay.date}"
            )
            SpacerHeight(Dimen.ExtraSmallSpace)
            RowContainTextWithIcon (
                imageVector = Icons.Filled.Thermostat,
                text = "Avg.Temperature: ${forecastDay.day.avgTempC} C"
            )
            SpacerHeight(Dimen.ExtraSmallSpace)
            RowContainTextWithIcon (
                imageVector = Icons.Filled.Air,
                text = "Max.Wind: ${forecastDay.day.maxWindMph} mph"
            )
            SpacerHeight(Dimen.ExtraSmallSpace)
            RowContainTextWithIcon (
                imageVector = Icons.Filled.Speed,
                text = "Pressure: ${forecastDay.day.totalPrecipIn}"
            )
            SpacerHeight(Dimen.ExtraSmallSpace)

            if (isExpanded) {
                LazyRow (
                    modifier = Modifier
                        .padding(Dimen.SmallSpace)
                ) {
                    items(forecastDay.hour) { hour ->
                        HourlyWeatherCard(hour)
                    }
                }
            }
        }
    }
}

@Composable
fun HourlyWeatherCard(hour: Hour) {
    Card(
        modifier = Modifier
            .padding(Dimen.ExtraSmallSpace)
            .width(100.dp),
        shape = RoundedCornerShape(Dimen.SmallSpace),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WeatherIcon(
                condition = hour.condition,
                size = 48
            )
            BaseText(hour.time.substring(11))
            BaseText("${hour.tempC}°C")
        }
    }
}