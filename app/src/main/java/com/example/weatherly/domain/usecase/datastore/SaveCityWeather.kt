package com.example.weatherly.domain.usecase.datastore

import com.example.weatherly.domain.datastore.DatastoreManager
import com.example.weatherly.domain.model.CityWeather

class SaveCityWeather (
    private val datastoreManager: DatastoreManager
) {
    suspend operator fun invoke(cityWeather: CityWeather) {
        datastoreManager.saveCityWeather(cityWeather)
    }
}