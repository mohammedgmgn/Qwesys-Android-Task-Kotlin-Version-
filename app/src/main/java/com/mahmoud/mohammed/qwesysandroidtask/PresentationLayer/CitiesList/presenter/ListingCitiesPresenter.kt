package com.mahmoud.mohammed.qwesysandroidtask.PresentationLayer.CitiesList.presenter

import android.app.Activity

import com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Callbacks
import com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Local.Model.CityModel
import com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Local.LocalDataRepository
import com.mahmoud.mohammed.qwesysandroidtask.PresentationLayer.CitiesList.ListingCitiesContract
import com.mahmoud.mohammed.qwesysandroidtask.PresentationLayer.CitiesList.view.ListingCitiesActivity
import com.mahmoud.mohammed.qwesysandroidtask.base.BaseView

import java.util.ArrayList

class ListingCitiesPresenter(baseView: BaseView, activity: Activity) : ListingCitiesContract.CitiesPresenterInterface {
    override fun setView(view: BaseView) {
        listingCitiesView = view as ListingCitiesContract.ListCitiesView

    }

    internal lateinit var listingCitiesView: ListingCitiesContract.ListCitiesView
    internal lateinit var mCityModel: CityModel
    internal var mDataRepo: LocalDataRepository
    internal var activity: ListingCitiesActivity
    internal var citiesModelList: MutableList<CityModel>

    init {
        this.activity = activity as ListingCitiesActivity
        setView(baseView)
        mDataRepo = LocalDataRepository()
        citiesModelList = ArrayList()
    }



    override fun saveNewCity(cityName: String) {
        mCityModel = CityModel(cityName)
        citiesModelList.add(mCityModel)
        listingCitiesView.onGetCitiesSuccess(citiesModelList)
        mCityModel.saveCityInLocalDb();
    }

    override fun getCities() {
        listingCitiesView.showProgress()
        mDataRepo.getCities(object : Callbacks.GetCitiesCallbacks{
            override fun onSuccess(result: List<CityModel>) {
                citiesModelList = result as MutableList<CityModel>
                listingCitiesView.hideProgress()
                listingCitiesView.onGetCitiesSuccess(result)

            }
            override fun onError(error: String) {
                listingCitiesView.hideProgress()
                listingCitiesView.showErrMsg(error)
            }
        })
    }

    override fun onItemInteraction(adapterPosition: Int) {
        listingCitiesView.startWeatherResultActivity(citiesModelList[adapterPosition])
    }

}
