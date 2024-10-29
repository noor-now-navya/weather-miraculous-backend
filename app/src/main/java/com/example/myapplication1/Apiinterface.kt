package com.example.weatherapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("weather")
    fun getWeatherData(
        @Query("q") city: String,
        @Query("appid") apiKey: String
    ): Call<WeatherResponse>
}

class WeatherResponse {
    package com.example.weatherapp

    data class WeatherResponse(
        val name: String, // City name
        val main: Main
    )

    data class Main(
        val temp: Float, // Temperature
        val humidity: Int // Humidity percentage
    )

}
