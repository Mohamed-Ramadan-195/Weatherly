package com.example.weatherly.presentation.screen.ForecastWeather.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherly.domain.usecase.weather.WeatherUseCases
import com.example.weatherly.presentation.screen.ForecastWeather.state.ForecastWeatherIntent
import com.example.weatherly.presentation.screen.ForecastWeather.state.ForecastWeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastWeatherViewModel @Inject constructor(
    private val weatherUseCases: WeatherUseCases
): ViewModel() {
    private var _forecastWeatherState = mutableStateOf(ForecastWeatherState())
    val forecastWeatherState: State<ForecastWeatherState> = _forecastWeatherState

    fun onIntent(forecastWeatherIntent: ForecastWeatherIntent) {
        when (forecastWeatherIntent) {
            is ForecastWeatherIntent.FetchForecastWeather -> getForecastWeather(
                latitude = forecastWeatherIntent.latitude,
                longitude = forecastWeatherIntent.longitude
            )
        }
    }

    private fun getForecastWeather(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            _forecastWeatherState.value = _forecastWeatherState.value.copy()
            try {
                val forecastWeather = weatherUseCases.getForecastWeatherUseCase(
                    latitude = latitude,
                    longitude = longitude
                )
                _forecastWeatherState.value = ForecastWeatherState(forecastWeather)
            } catch (exception: Exception) {
                _forecastWeatherState.value = ForecastWeatherState()
            }
        }
    }
}