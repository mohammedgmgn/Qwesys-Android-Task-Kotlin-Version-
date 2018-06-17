package com.mahmoud.mohammed.qwesysandroidtask.PresentationLayer.WeatherResult

import android.content.Intent
import android.view.View

import com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Remote.model.CurrentWeatherInfo
import com.mahmoud.mohammed.qwesysandroidtask.base.BasePresenter
import com.mahmoud.mohammed.qwesysandroidtask.base.BaseView

interface WeatherResultContract {
    interface WeatherResultView : BaseView {
        fun showWeatherResult(currentWeatherInfo: CurrentWeatherInfo)
        fun showProgress()
        fun hideProgress()

    }

    interface WeatherResultPresenterInterface : BasePresenter<View> {
        fun getWeatherData(intent: Intent)
    }

}
