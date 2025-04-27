package com.example.weatherly.domain.datastore

import kotlinx.coroutines.flow.Flow

interface DatastoreManager {
    suspend fun saveCityWeather(city: String)
    fun readCityWeather(): Flow<String>
    suspend fun clearDataStore()
}