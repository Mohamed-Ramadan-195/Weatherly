package com.example.weatherly.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.weatherly.domain.manager.DatastoreManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DatastoreManagerImpl (
    private val context: Context
): DatastoreManager {
    override suspend fun saveCityWeather(city: String) {
        context.preferenceDataStore.edit {
            it[CITY] = city
        }
    }

    override fun readCityWeather(): Flow<String> {
        return context.preferenceDataStore.data.map {
            it[CITY] ?: ""
        }
    }

    companion object {
        val CITY = stringPreferencesKey("CITY")
    }

    override suspend fun clearDataStore() {
        context.preferenceDataStore.edit {
            it.clear()
        }
    }
}

const val CITY_DATASTORE = "city_data"

val Context.preferenceDataStore: DataStore<Preferences> by preferencesDataStore(name = CITY_DATASTORE)