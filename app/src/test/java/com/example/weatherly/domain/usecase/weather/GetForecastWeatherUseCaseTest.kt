package com.example.weatherly.domain.usecase.weather

import com.example.weatherly.domain.repository.WeatherRepository
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class GetForecastWeatherUseCaseTest {

    private lateinit var weatherRepository: WeatherRepository
    private lateinit var getForecastWeatherUseCase: GetForecastWeatherUseCase

    @Before
    fun setup() {
        weatherRepository = mock()
        getForecastWeatherUseCase = GetForecastWeatherUseCase(weatherRepository)
    }

    @Test
    fun `should return forecast weather from repository`() = runTest {
        val city = "Cairo"
        val expectedWeather = createForecastWeather("cairo")

        whenever(weatherRepository.getForecastWeather(city)).thenReturn(flowOf(expectedWeather))

        val result = getForecastWeatherUseCase.invoke(city).first()

        TestCase.assertEquals(expectedWeather, result)
        verify(weatherRepository, times(1)).getForecastWeather(city)
    }
}