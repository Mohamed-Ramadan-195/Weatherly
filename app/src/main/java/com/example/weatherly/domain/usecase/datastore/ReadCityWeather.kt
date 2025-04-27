package com.example.weatherly.domain.usecase.datastore

import com.example.weatherly.domain.datastore.DatastoreManager
import kotlinx.coroutines.flow.Flow

class ReadCityWeather (
    private val datastoreManager: DatastoreManager
) {
    operator fun invoke(): Flow<String> = datastoreManager.readCityWeather()
}