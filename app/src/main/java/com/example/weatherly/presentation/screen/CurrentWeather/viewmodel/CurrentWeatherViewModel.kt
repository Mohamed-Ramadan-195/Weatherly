package com.example.weatherly.presentation.screen.CurrentWeather.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherly.domain.usecase.datastore.CityWeatherUseCases
import com.example.weatherly.domain.usecase.weather.WeatherUseCases
import com.example.weatherly.presentation.screen.CurrentWeather.state.CurrentWeatherEvent
import com.example.weatherly.presentation.screen.CurrentWeather.state.CurrentWeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val weatherUseCases: WeatherUseCases,
    private val cityWeatherUseCases: CityWeatherUseCases
): ViewModel() {
    private var _currentWeatherState = mutableStateOf(CurrentWeatherState())
    val currentWeatherState: State<CurrentWeatherState> = _currentWeatherState

    init {
        readCityWeather()
    }

    fun onEvent(event: CurrentWeatherEvent) {
        when (event) {
            is CurrentWeatherEvent.UpdateCity -> {
                _currentWeatherState.value = _currentWeatherState.value.copy(
                    city = event.city
                )
            }

            is CurrentWeatherEvent.CityWeather -> {
                getCurrentWeather()
            }
        }
    }

    private fun getCurrentWeather() {
        viewModelScope.launch {
            weatherUseCases.getCurrentWeatherUseCase(city = currentWeatherState.value.city)
                .collect { currentWeather ->
                    _currentWeatherState.value = _currentWeatherState.value.copy(
                        currentWeather = currentWeather
                    )

                    saveCityWeather(
                        city = currentWeather.location.name
                    )
                }
        }
    }

    private fun saveCityWeather(city: String) {
        viewModelScope.launch {
            cityWeatherUseCases.saveCityWeather(city)
        }
    }

    private fun readCityWeather() {
        viewModelScope.launch {
            cityWeatherUseCases.readCityWeather().collectLatest { savedCityWeather ->
                _currentWeatherState.value = _currentWeatherState.value.copy(
                    city = savedCityWeather
                )
                getCurrentWeather()
            }
        }
    }
}