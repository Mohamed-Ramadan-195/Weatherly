package com.example.weatherly.domain.usecase.datastore

import com.example.weatherly.domain.datastore.DatastoreManager

class SaveCityWeather (
    private val datastoreManager: DatastoreManager
) {
    suspend operator fun invoke(city: String) {
        datastoreManager.saveCityWeather(city)
    }
}