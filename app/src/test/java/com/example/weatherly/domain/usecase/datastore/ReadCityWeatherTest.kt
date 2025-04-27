package com.example.weatherly.domain.usecase.datastore

import com.example.weatherly.domain.manager.DatastoreManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.mockito.Mockito.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class ReadCityWeatherTest {

    private lateinit var datastoreManager: DatastoreManager
    private lateinit var readCityWeather: ReadCityWeather

    @Before
    fun setUp() {
        datastoreManager = mock()
        readCityWeather = ReadCityWeather(datastoreManager)
    }

    @Test
    fun `should read city weather from datastore`() = runTest {
        // Given
        val expectedCity = "Cairo"
        whenever(datastoreManager.readCityWeather()).thenReturn(flowOf(expectedCity))

        // When
        val result = readCityWeather.invoke().first()

        // Then
        assertEquals(expectedCity, result)
        verify(datastoreManager, times(1)).readCityWeather()
    }
}