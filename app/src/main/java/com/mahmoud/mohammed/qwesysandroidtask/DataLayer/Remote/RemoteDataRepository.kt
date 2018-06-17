package com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Remote

import android.annotation.SuppressLint

import com.mahmoud.mohammed.qwesysandroidtask.BuildConfig
import com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Callbacks
import com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Remote.Services.WeatherDataService

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RemoteDataRepository : RemoteRepositorySource {
    @SuppressLint("CheckResult")
    override fun getCities(weatherDataService: WeatherDataService, cityName: String, callbacks: Callbacks.GetWeatherCallbacks) {
        weatherDataService.getCurrentWeatherData(BuildConfig.THE_WEATHER_API_KEY, cityName).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({ currentWeatherInfo -> callbacks.onSuccess(currentWeatherInfo) }) { error -> callbacks.onError(error.message!!) }

    }
}
