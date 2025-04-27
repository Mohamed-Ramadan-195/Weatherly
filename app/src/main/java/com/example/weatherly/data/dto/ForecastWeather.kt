package com.example.weatherly.data.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastWeather(
    val current: Current,
    val forecast: Forecast,
    val location: Location
): Parcelable {
    companion object {
        fun default(): ForecastWeather {
            return ForecastWeather(
                current = Current (
                    condition = Condition(
                        code = 0,
                        icon = "",
                        text = ""
                    ),
                    feelsLikeC = 0.0,
                    feelsLikeF = 0.0,
                    humidity = 0,
                    isDay = 0,
                    lastUpdated = "",
                    lastUpdatedEpoch = 0,
                    precipIn = 0.0,
                    precipMm = 0.0,
                    temperatureC = 0.0,
                    temperatureF = 0.0,
                    uv = 0.0,
                    visKm = 0.0,
                    visMiles = 0.0,
                    windDegree = 0,
                    windDir = "",
                    windKph = 0.0,
                    windMph = 0.0,
                    windchillC = 0.0,
                    windchillF = 0.0,
                    cloud = 0,
                    dewpointC = 0.0,
                    dewpointF = 0.0,
                    pressureMb = 0.0,
                    pressureIn = 0.0,
                    gustKph = 0.0,
                    gustMph = 0.0,
                    heatindexC = 0.0,
                    heatindexF = 0.0,
                ),
                forecast = Forecast(forecastDay = emptyList()),
                location = Location(
                    country = "",
                    lat = 0.0,
                    localtime = "",
                    localtimeEpoch = 0,
                    lon = 0.0,
                    name = "",
                    region = "",
                    tzId = "",
                )
            )
        }
    }
}

@Parcelize
data class Forecast(
    @SerializedName("forecastday")
    val forecastDay: List<Forecastday>
): Parcelable

@Parcelize
data class Forecastday(
    @SerializedName("astro")
    val astro: Astro,
    val date: String,
    @SerializedName("date_epoch")
    val dateEpoch: Int,
    val day: Day,
    val hour: List<Hour>
): Parcelable

@Parcelize
data class Astro(
    @SerializedName("is_moon_up")
    val isMoonUp: Int,
    @SerializedName("is_sun_up")
    val isSunUp: Int,
    @SerializedName("moon_illumination")
    val moonIllumination: Int,
    @SerializedName("moon_phase")
    val moonPhase: String,
    val moonrise: String,
    @SerializedName("moonset")
    val moonSet: String,
    val sunrise: String,
    val sunset: String
): Parcelable

@Parcelize
data class Day(
    @SerializedName("avghumidity")
    val avghumidity: Int,
    @SerializedName("avgtemp_c")
    val avgTempC: Double,
    @SerializedName("avgtemp_f")
    val avgTempF: Double,
    @SerializedName("avgvis_km")
    val avgvisKm: Double,
    @SerializedName("avgvis_miles")
    val avgvisMiles: Double,
    val condition: Condition,
    @SerializedName("daily_chance_of_rain")
    val dailyChanceOfRain: Int,
    @SerializedName("daily_chance_of_snow")
    val dailyChanceOfSnow: Int,
    @SerializedName("daily_will_it_rain")
    val dailyWillItRain: Int,
    @SerializedName("daily_will_it_snow")
    val dailyWillItSnow: Int,
    @SerializedName("maxtemp_c")
    val maxTempC: Double,
    @SerializedName("maxtemp_f")
    val maxTempF: Double,
    @SerializedName("maxwind_kph")
    val maxWindKph: Double,
    @SerializedName("maxwind_mph")
    val maxWindMph: Double,
    @SerializedName("mintemp_c")
    val minTempC: Double,
    @SerializedName("mintemp_f")
    val minTempF: Double,
    @SerializedName("totalprecip_in")
    val totalPrecipIn: Double,
    @SerializedName("totalprecip_mm")
    val totalPrecipMm: Double,
    @SerializedName("totalsnow_cm")
    val totalSnowCm: Double,
    val uv: Double
): Parcelable

@Parcelize
data class Hour(
    @SerializedName("chance_of_rain")
    val chanceOfRain: Int,
    @SerializedName("chance_of_snow")
    val chanceOfSnow: Int,
    val cloud: Int,
    val condition: Condition,
    @SerializedName("dewpoint_c")
    val dewpointC: Double,
    @SerializedName("dewpoint_f")
    val dewpointF: Double,
    @SerializedName("feelslike_c")
    val feelslikeC: Double,
    @SerializedName("feelslike_f")
    val feelslikeF: Double,
    @SerializedName("gust_kph")
    val gustkph: Double,
    @SerializedName("gust_mph")
    val gustMph: Double,
    @SerializedName("heatindex_c")
    val heatindexC: Double,
    @SerializedName("heatindex_f")
    val heatindexF: Double,
    val humidity: Int,
    @SerializedName("is_day")
    val isDay: Int,
    @SerializedName("precip_in")
    val precipIn: Double,
    @SerializedName("precip_mm")
    val precipMm: Double,
    @SerializedName("pressure_in")
    val pressureIn: Double,
    @SerializedName("pressure_mb")
    val pressureMb: Double,
    @SerializedName("snow_cm")
    val snowCm: Double,
    @SerializedName("temp_c")
    val tempC: Double,
    @SerializedName("temp_f")
    val tempF: Double,
    val time: String,
    @SerializedName("time_epoch")
    val timeEpoch: Int,
    val uv: Double,
    @SerializedName("vis_km")
    val visKm: Double,
    @SerializedName("vis_miles")
    val visMiles: Double,
    @SerializedName("will_it_rain")
    val willItRain: Int,
    @SerializedName("will_it_snow")
    val willItSnow: Int,
    @SerializedName("wind_degree")
    val windDegree: Int,
    @SerializedName("wind_dir")
    val windDir: String,
    @SerializedName("wind_kph")
    val windKph: Double,
    @SerializedName("wind_mph")
    val windMph: Double,
    @SerializedName("windchill_c")
    val windchillC: Double,
    @SerializedName("windchill_f")
    val windchillF: Double
): Parcelable