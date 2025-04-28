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
                city = forecastWeatherIntent.city
            )
        }
    }

    private fun getForecastWeather(city: String) {
        viewModelScope.launch {
            weatherUseCases.getForecastWeatherUseCase(city)
                .collect { forecastWeather ->
                    _forecastWeatherState.value = _forecastWeatherState.value.copy(
                        forecastWeather = forecastWeather
                    )
                }
        }
    }
}