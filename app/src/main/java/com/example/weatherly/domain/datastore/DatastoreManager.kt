package com.example.weatherly.domain.datastore

import com.example.weatherly.domain.model.CityWeather
import kotlinx.coroutines.flow.Flow

interface DatastoreManager {
    suspend fun saveCityWeather(cityWeather: CityWeather)
    fun readCityWeather(): Flow<CityWeather>
    suspend fun clearDataStore()
}