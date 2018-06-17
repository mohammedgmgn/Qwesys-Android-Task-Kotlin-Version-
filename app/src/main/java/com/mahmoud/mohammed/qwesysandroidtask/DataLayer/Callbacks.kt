package com.mahmoud.mohammed.qwesysandroidtask.DataLayer

import com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Local.Model.CityModel
import com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Remote.model.CurrentWeatherInfo

interface Callbacks {
    interface BaseNetworkCallbacks<OnSuccessReturnType> {
        fun onSuccess(result: OnSuccessReturnType)
        fun onError(err: String)
    }

    interface GetCitiesCallbacks : BaseNetworkCallbacks<List<CityModel>>
    interface GetWeatherCallbacks : BaseNetworkCallbacks<CurrentWeatherInfo>

}
