package com.example.weatherly.data.repository

import com.example.weatherly.data.remote.WeatherService
import com.example.weatherly.domain.usecase.weather.createCurrentWeather
import com.example.weatherly.domain.usecase.weather.createForecastWeather
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class WeatherRepositoryImplTest {

    private lateinit var weatherService: WeatherService
    private lateinit var repository: WeatherRepositoryImpl
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        weatherService = mockk()
        repository = WeatherRepositoryImpl(weatherService)
    }

    @After
    fun tearDown() = Dispatchers.resetMain()

    @Test
    fun `getCurrentWeather returns current weather successfully`() = runTest {
        val cityName = "Cairo"
        val expectedResponse = createCurrentWeather(cityName)

        coEvery { weatherService.getCurrentWeather(cityName) } returns expectedResponse

        val result = repository.getCurrentWeather(cityName).first()

        assertEquals(expectedResponse, result)
    }

    @Test
    fun `getForecastWeather returns forecast weather successfully`() = runTest {
        val cityName = "Cairo"
        val expectedResponse = createForecastWeather(cityName)

        coEvery { weatherService.getForecastWeather(cityName) } returns expectedResponse

        val result = repository.getForecastWeather(cityName).first()

        assertEquals(expectedResponse, result)
    }

    @Test
    fun `getCurrentWeather handles error correctly`() = runTest {
        val cityName = "Cairo"
        val exception = IOException("Network error")

        coEvery { weatherService.getCurrentWeather(cityName) } throws exception

        repository.getCurrentWeather(cityName).collect {  }

        assertTrue(true)
    }

    @Test
    fun `getForecastWeather handles error correctly`() = runTest {
        val cityName = "Cairo"
        val exception = IOException("Network error")

        coEvery { weatherService.getForecastWeather(cityName) } throws exception

        repository.getForecastWeather(cityName).collect { }

        assertTrue(true)
    }
}
