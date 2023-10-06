package com.example.test

data class WeatherResponse(
    val list: List<WeatherData>
)

data class WeatherData(
    val dt: Long,
    val main: MainData
)

data class MainData(
    val temp: Double
)