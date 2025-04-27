package com.example.weatherly.domain.usecase.weather

import com.example.weatherly.domain.repository.WeatherRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class GetCurrentWeatherUseCaseTest {

    private lateinit var weatherRepository: WeatherRepository
    private lateinit var getCurrentWeatherUseCase: GetCurrentWeatherUseCase

    @Before
    fun setup() {
        weatherRepository = mock(WeatherRepository::class.java)
        getCurrentWeatherUseCase = GetCurrentWeatherUseCase(weatherRepository)
    }

    @Test
    fun `should return current weather from repository`() = runTest {
        val city = "Cairo"
        val expectedWeather = createCurrentWeather("cairo")

        whenever(weatherRepository.getCurrentWeather(city)).thenReturn(flowOf(expectedWeather))

        val result = getCurrentWeatherUseCase.invoke(city).first()

        assertEquals(expectedWeather, result)
        verify(weatherRepository, times(1)).getCurrentWeather(city)
    }


}