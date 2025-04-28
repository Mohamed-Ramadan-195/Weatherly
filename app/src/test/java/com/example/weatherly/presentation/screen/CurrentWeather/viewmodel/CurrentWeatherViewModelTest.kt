package com.example.weatherly.presentation.screen.CurrentWeather.viewmodel

import com.example.weatherly.domain.usecase.datastore.CityWeatherUseCases
import com.example.weatherly.domain.usecase.weather.WeatherUseCases
import com.example.weatherly.domain.usecase.weather.createCurrentWeather
import com.example.weatherly.presentation.screen.CurrentWeather.state.CurrentWeatherEvent
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CurrentWeatherViewModelTest {

    private lateinit var weatherUseCases: WeatherUseCases
    private lateinit var cityWeatherUseCases: CityWeatherUseCases
    private lateinit var currentWeatherViewModel: CurrentWeatherViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        weatherUseCases = mockk()
        cityWeatherUseCases = mockk()

        coEvery { cityWeatherUseCases.readCityWeather() } returns flowOf("Cairo")
        coEvery { cityWeatherUseCases.saveCityWeather(any()) } just Runs
        coEvery { weatherUseCases.getCurrentWeatherUseCase(any()) } returns flowOf(
            createCurrentWeather("Cairo")
        )

        currentWeatherViewModel = CurrentWeatherViewModel(weatherUseCases, cityWeatherUseCases)
    }

    @After
    fun tearDown() = Dispatchers.resetMain()

    @Test
    fun `test initial state and readCityWeather call`() = runTest {
        advanceUntilIdle()

        assertEquals("Cairo", currentWeatherViewModel.currentWeatherState.value.city)
        assertEquals(
            "Cairo",
            currentWeatherViewModel.currentWeatherState.value.currentWeather.location.name
        )
    }

    @Test
    fun `test onEvent UpdateCity updates city in state`() = runTest {
        currentWeatherViewModel.onEvent(CurrentWeatherEvent.UpdateCity("Alexandria"))
        assertEquals("Alexandria", currentWeatherViewModel.currentWeatherState.value.city)
    }

    @Test
    fun `should test saveCityWeather when called inside getCurrentWeather`() = runTest {
        coEvery { cityWeatherUseCases.saveCityWeather(any()) } just Runs

        currentWeatherViewModel.onEvent(CurrentWeatherEvent.UpdateCity("Giza"))
        currentWeatherViewModel.onEvent(CurrentWeatherEvent.CityWeather)
        advanceUntilIdle()

        coVerify { cityWeatherUseCases.saveCityWeather("Cairo") }
    }
}
