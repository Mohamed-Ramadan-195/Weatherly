package com.example.weatherly.domain.usecase.datastore

import com.example.weatherly.domain.manager.DatastoreManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class SaveCityWeatherTest {

    private lateinit var datastoreManager: DatastoreManager
    private lateinit var saveCityWeather: SaveCityWeather

    @Before
    fun setUp() {
        datastoreManager = mock()
        saveCityWeather = SaveCityWeather(datastoreManager)
    }

    @Test
    fun `should save city weather in preference datastore`() = runTest {
        val city = "Cairo"

        saveCityWeather.invoke(city)

        verify(datastoreManager, times(1)).saveCityWeather(city)
    }
}