package com.mahmoud.mohammed.qwesysandroidtask.PresentationLayer.WeatherResult.presenter

import android.content.Context
import android.content.Intent

import com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Callbacks
import com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Local.Model.CityModel
import com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Remote.RemoteDataRepository
import com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Remote.RetrofitModule
import com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Remote.Services.WeatherDataService
import com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Remote.model.CurrentWeatherInfo
import com.mahmoud.mohammed.qwesysandroidtask.PresentationLayer.WeatherResult.WeatherResultContract
import com.mahmoud.mohammed.qwesysandroidtask.PresentationLayer.WeatherResult.view.WeatherResultActivity
import com.mahmoud.mohammed.qwesysandroidtask.R
import com.mahmoud.mohammed.qwesysandroidtask.base.BaseView

class WeatherResultPresenter(baseView: BaseView, internal var activity: WeatherResultActivity, internal var mContext: Context) : WeatherResultContract.WeatherResultPresenterInterface {
    internal lateinit var weatherResultView: WeatherResultContract.WeatherResultView
    internal var weatherDataService: WeatherDataService
    internal var mRemoteDataRepo: RemoteDataRepository

    override fun getWeatherData(intent: Intent) {
        val cityObj = intent.getParcelableExtra<CityModel>(activity.getString(R.string.city_obj_key))
        getCityWeatherData(cityObj.cityName!!)

    }


    override fun setView(view: BaseView) {
        weatherResultView = view as WeatherResultContract.WeatherResultView
    }

    init {
        setView(baseView)
        weatherDataService = RetrofitModule.provideRetrofit(mContext).create(WeatherDataService::class.java)
        mRemoteDataRepo = RemoteDataRepository()
    }

    private fun getCityWeatherData(cityName: String) {
        weatherResultView.showProgress()
        mRemoteDataRepo.getCities(weatherDataService, cityName, object : Callbacks.GetWeatherCallbacks {
            override fun onSuccess(result: CurrentWeatherInfo) {
                weatherResultView.hideProgress()
                weatherResultView.showWeatherResult(result as CurrentWeatherInfo)
            }



            override fun onError(error: String) {
                weatherResultView.showErrMsg(error)
            }
        })
    }

}
