package com.mahmoud.mohammed.qwesysandroidtask.PresentationLayer.WeatherResult.view

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

import com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Remote.model.CurrentWeatherInfo
import com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Remote.model.TemperatureInfoData
import com.mahmoud.mohammed.qwesysandroidtask.PresentationLayer.WeatherResult.WeatherResultContract
import com.mahmoud.mohammed.qwesysandroidtask.PresentationLayer.WeatherResult.presenter.WeatherResultPresenter
import com.mahmoud.mohammed.qwesysandroidtask.R

import butterknife.BindView
import butterknife.ButterKnife
import kotterknife.bindView

class WeatherResultActivity : AppCompatActivity(), WeatherResultContract.WeatherResultView {
    internal lateinit var presenter: WeatherResultPresenter

    val cityNameTv: TextView by bindView(R.id.city_name_tv)
    val highTemp: TextView by bindView(R.id.high_temperature)
    val lowTemp: TextView by bindView(R.id.low_temperature)
    val progressBar: ProgressBar by bindView(R.id.weather_progress_bar)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_result_screen)
        ButterKnife.bind(this)
        presenter = WeatherResultPresenter(this, this, this)
        presenter.getWeatherData(intent)

    }

    override fun showWeatherResult(currentWeatherInfo: CurrentWeatherInfo) {
        cityNameTv.text = currentWeatherInfo.cityName
        highTemp.text = currentWeatherInfo.temperature.temp_max.toString() + "\u00b0"
        lowTemp.text = currentWeatherInfo.temperature.temp_min.toString() + "\u00b0"
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()

    }

    override fun showErrMsg(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()

    }

    override fun showNoInternetMsg() {

    }
}
