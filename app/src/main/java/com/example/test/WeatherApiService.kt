package com.example.test

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("data/2.5/forecast")
    fun getWeatherForecast(
        @Query("q") cityName: String,
        @Query("cnt") days: Int,
        @Query("appid") apiKey: String
    ): Call<WeatherResponse>
}