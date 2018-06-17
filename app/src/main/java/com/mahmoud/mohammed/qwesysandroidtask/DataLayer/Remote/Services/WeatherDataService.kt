package com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Remote.Services

import com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Remote.model.CurrentWeatherInfo

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface WeatherDataService {
    @GET("weather?mode=json")
    fun getCurrentWeatherData(@Query("apikey") apiKey: String, @Query("q") cityName: String): Observable<CurrentWeatherInfo>

}
