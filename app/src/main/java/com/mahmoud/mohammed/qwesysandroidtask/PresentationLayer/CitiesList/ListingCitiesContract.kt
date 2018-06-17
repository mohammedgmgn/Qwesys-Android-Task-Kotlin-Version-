package com.mahmoud.mohammed.qwesysandroidtask.PresentationLayer.CitiesList

import android.view.View
import com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Local.Model.CityModel
import com.mahmoud.mohammed.qwesysandroidtask.base.BasePresenter
import com.mahmoud.mohammed.qwesysandroidtask.base.BaseView


public interface ListingCitiesContract {


    interface ListCitiesView : BaseView {
        fun startWeatherResultActivity(selectedCity: CityModel)

        fun onCitySavedSuccesufully()

        fun onCityFaillToSave()

        fun onGetCitiesSuccess(cityModelList: List<CityModel>)

        fun showProgress()

        fun hideProgress()

    }

    interface CitiesPresenterInterface : BasePresenter<View> {
        fun saveNewCity(cityName: String)

        fun getCities()

        fun onItemInteraction(adapterPosition: Int)

    }

}
