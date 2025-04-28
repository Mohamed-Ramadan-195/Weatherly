package com.example.weatherly.presentation.screen.ForecastWeather.viewmodel

import com.example.weatherly.domain.usecase.weather.WeatherUseCases
import com.example.weatherly.domain.usecase.weather.createForecastWeather
import com.example.weatherly.presentation.screen.ForecastWeather.state.ForecastWeatherIntent
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ForecastWeatherViewModelTest {

    private lateinit var weatherUseCases: WeatherUseCases
    private lateinit var viewModel: ForecastWeatherViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        weatherUseCases = mockk()

        coEvery { weatherUseCases.getForecastWeatherUseCase(any()) } returns flowOf(
            createForecastWeather("Cairo")
        )

        viewModel = ForecastWeatherViewModel(weatherUseCases)
    }

    @After
    fun tearDown() = Dispatchers.resetMain()

    @Test
    fun `test onIntent FetchForecastWeather triggers getForecastWeather and updates state`() = runTest {
        val cityName = "Cairo"

        viewModel.onIntent(ForecastWeatherIntent.FetchForecastWeather(cityName))
        advanceUntilIdle()

        assertNotNull(viewModel.forecastWeatherState.value.forecastWeather)
        assertEquals(
            "Cairo",
            viewModel.forecastWeatherState.value.forecastWeather.location.name
        )
    }
}
