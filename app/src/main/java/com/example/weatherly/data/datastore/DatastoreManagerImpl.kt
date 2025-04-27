package com.example.weatherly.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.weatherly.data.utils.DataConstant
import com.example.weatherly.domain.datastore.DatastoreManager
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

val Context.preferenceDataStore: DataStore<Preferences> by preferencesDataStore(name = DataConstant.CITY_DATASTORE)