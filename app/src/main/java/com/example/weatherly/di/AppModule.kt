package com.example.weatherly.di

import android.app.Application
import com.example.weatherly.data.datastore.DatastoreManagerImpl
import com.example.weatherly.data.remote.WeatherService
import com.example.weatherly.data.repository.WeatherRepositoryImpl
import com.example.weatherly.domain.datastore.DatastoreManager
import com.example.weatherly.domain.repository.WeatherRepository
import com.example.weatherly.domain.usecase.datastore.CityWeatherUseCases
import com.example.weatherly.domain.usecase.weather.GetCurrentWeatherUseCase
import com.example.weatherly.domain.usecase.datastore.ReadCityWeather
import com.example.weatherly.domain.usecase.datastore.SaveCityWeather
import com.example.weatherly.domain.usecase.weather.WeatherUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherService {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(WeatherService::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(
        weatherService: WeatherService
    ): WeatherRepository = WeatherRepositoryImpl(weatherService)

    @Provides
    @Singleton
    fun provideWeatherUseCases (
        weatherRepository: WeatherRepository
    ): WeatherUseCases {
        return WeatherUseCases(
            getCurrentWeatherUseCase = GetCurrentWeatherUseCase(weatherRepository)
        )
    }

    @Provides
    @Singleton
    fun provideDatastore(
        application: Application
    ): DatastoreManager = DatastoreManagerImpl(application)

    @Provides
    @Singleton
    fun provideCityWeatherUseCases(
        datastoreManager: DatastoreManager
    ) = CityWeatherUseCases(
        saveCityWeather = SaveCityWeather(datastoreManager),
        readCityWeather = ReadCityWeather(datastoreManager)
    )
}