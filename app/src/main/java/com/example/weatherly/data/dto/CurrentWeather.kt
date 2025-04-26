package com.example.weatherly.data.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentWeather(
    @SerializedName("base")
    val base: String,
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("cod")
    val cod: Int,
    @SerializedName("coord")
    val coord: Coord,
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: Main,
    @SerializedName("name")
    val name: String,
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("timezone")
    val timezone: Int,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind")
    val wind: Wind
): Parcelable {
    companion object {
        fun default() = CurrentWeather(
            base = "",
            clouds = Clouds(0),
            cod = 0,
            coord = Coord(0.0, 0.0),
            dt = 0,
            id = 0,
            main = Main(0.0, 0, 0, 0, 0, 0.0, 0.0, 0.0),
            name = "",
            sys = Sys("", 0, 0, 0, 0),
            timezone = 0,
            visibility = 0,
            weather = listOf(Weather("", "", 0, "")),
            wind = Wind(0, 0.0)
        )
    }
}

@Parcelize
data class Clouds(
    @SerializedName("all")
    val all: Int
): Parcelable

@Parcelize
data class Coord(
    @SerializedName("lat")
    val latitude: Double,
    @SerializedName("lon")
    val longitude: Double
): Parcelable

@Parcelize
data class Main(
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("grnd_level")
    val grndLevel: Int,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("sea_level")
    val seaLevel: Int,
    @SerializedName("temp")
    val temperature: Double,
    @SerializedName("temp_max")
    val tempMaximum: Double,
    @SerializedName("temp_min")
    val tempMinimum: Double
): Parcelable

@Parcelize
data class Sys(
    @SerializedName("country")
    val country: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("sunrise")
    val sunrise: Int,
    @SerializedName("sunset")
    val sunset: Int,
    @SerializedName("type")
    val type: Int
): Parcelable

@Parcelize
data class Weather(
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String
): Parcelable

@Parcelize
data class Wind(
    @SerializedName("deg")
    val degrees: Int,
    @SerializedName("speed")
    val speed: Double
): Parcelable