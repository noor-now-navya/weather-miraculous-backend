package com.example.myapplication1

import android.os.Bundle
import
android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


// b8a3f3efbd230d4e7cef1109c17048b4

// Main Activity Class
class MainActivity : AppCompatActivity() {

    // Base URL for the Weather API
    private val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    private val API_KEY = "b8a3f3efbd230d4e7cef1109c17048b4" // Replace with your API key

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Fetch weather data for New Delhi
        fetchWeatherData("New Delhi")
    }

    // Function to fetch weather data
    private fun fetchWeatherData(cityName: String) {
        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create an instance of the API interface
        val apiInterface = retrofit.create(ApiInterface::class.java)

        // Call the API to fetch weather data
        val call = apiInterface.getWeatherData(cityName, API_KEY)

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    val weatherResponse = response.body()
                    if (weatherResponse != null) {
                        // Handle the response (e.g., update UI)
                        val weatherInfo = "City: ${weatherResponse.name}\n" +
                                "Temperature: ${weatherResponse.main.temp}°C\n" +
                                "Humidity: ${weatherResponse.main.humidity}%"
                        Toast.makeText(this@MainActivity, weatherInfo, Toast.LENGTH_LONG).show()
                    }
                } else {
                    // Handle unsuccessful responses
                    Toast.makeText(this@MainActivity, "Failed to fetch data: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                // Handle API call failure
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
}











