package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]

        val weatherTextView = findViewById<TextView>(R.id.weatherTextView)

        weatherViewModel.weatherForecast.observe(this) { weatherForecastList ->
            val weatherStringBuilder = StringBuilder()
            weatherForecastList.forEach { weatherForecast ->
                weatherStringBuilder.append("${weatherForecast.date}: ${weatherForecast.temperature}°C\n")
            }
            weatherTextView.text = weatherStringBuilder.toString()
        }
    }
}
