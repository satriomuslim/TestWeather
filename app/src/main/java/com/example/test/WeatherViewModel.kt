package com.example.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class WeatherViewModel : ViewModel() {
    private val _weatherForecast = MutableLiveData<List<WeatherForecast>>()
    val weatherForecast: LiveData<List<WeatherForecast>> = _weatherForecast

    private val apiKey = "44f7c1b124ed78171760985654c00425"
    private val cityName = "Jakarta"
    private val numberOfDays = 5

    init {
        fetchWeatherForecast()
    }

    private fun fetchWeatherForecast() {
        val weatherService = RetrofitClient.weatherService
        val call = weatherService.getWeatherForecast(cityName, numberOfDays, apiKey)

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.isSuccessful) {
                    val weatherDataList = response.body()?.list
                    val weatherForecastList = weatherDataList?.map { weatherData ->
                        WeatherForecast(
                            formatDate(weatherData.dt),
                            weatherData.main.temp
                        )
                    }
                    _weatherForecast.postValue(weatherForecastList)
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }

    private fun formatDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault())
        return sdf.format(Date(timestamp * 1000))
    }
}