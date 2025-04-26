package com.example.weatherly.presentation.common

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.weatherly.R
import com.example.weatherly.data.dto.Weather
import com.example.weatherly.presentation.utils.Constant

@Composable
fun WeatherIcon(
    weather: Weather
) {
    val context = LocalContext.current
    AsyncImage (
        modifier = Modifier.size(100.dp),
        model = ImageRequest.Builder(context)
            .data("${Constant.BASE_IMAGE_URL}${weather.icon}@2x.png")
            .crossfade(true).build(),
        contentDescription = "Weather Icon",
        contentScale = ContentScale.FillBounds,
        placeholder = painterResource(R.drawable.cloudy),
        error = painterResource(R.drawable.green_coludy),
    )
}