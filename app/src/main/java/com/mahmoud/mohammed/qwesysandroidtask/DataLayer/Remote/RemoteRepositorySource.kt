package com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Remote

import com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Callbacks
import com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Remote.Services.WeatherDataService

interface RemoteRepositorySource {
    fun getCities(weatherDataService: WeatherDataService, cityName: String, callbacks: Callbacks.GetWeatherCallbacks)

}
