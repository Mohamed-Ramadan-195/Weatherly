package com.example.weatherly.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.weatherly.data.utils.DataConstant
import com.example.weatherly.domain.datastore.DatastoreManager
import com.example.weatherly.domain.model.CityWeather
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DatastoreManagerImpl (
    private val context: Context
): DatastoreManager {
    override suspend fun saveCityWeather(cityWeather: CityWeather) {
        context.preferenceDataStore.edit {
            it[CITY] = cityWeather.cityName
            it[ID] = cityWeather.id
        }
    }

    override fun readCityWeather(): Flow<CityWeather> {
        return context.preferenceDataStore.data.map {
            CityWeather(
                cityName = it[CITY] ?: "",
                id = it[ID] ?: 0
            )
        }
    }

    companion object {
        val CITY = stringPreferencesKey("CITY")
        val ID = intPreferencesKey("ID")
    }

    override suspend fun clearDataStore() {
        context.preferenceDataStore.edit {
            it.clear()
        }
    }
}

val Context.preferenceDataStore: DataStore<Preferences> by preferencesDataStore(name = DataConstant.CITY_DATASTORE)