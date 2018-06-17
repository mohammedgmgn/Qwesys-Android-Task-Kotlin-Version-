package com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Local

import com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Local.Model.CityModel

import java.util.ArrayList

import io.realm.Realm
import io.realm.RealmAsyncTask

object RealmQueries {
    private var transaction: RealmAsyncTask? = null

    fun instanceOfRealm(): Realm
       {
            return Realm.getDefaultInstance()
        }

    fun saveCityData(cityData: CityModel) {
        try {
            transaction=  instanceOfRealm().executeTransactionAsync({ realm -> realm.copyToRealmOrUpdate(cityData) })
        } finally {
            instanceOfRealm().close()
        }
    }

    fun getCitiesList():ArrayList<CityModel> {
        val cityModelList = ArrayList<CityModel>()

        instanceOfRealm().executeTransaction { realm ->
            val cityModels = instanceOfRealm().where(CityModel::class.java).findAll()
            if (cityModels != null && cityModels.size > 0) {
                cityModelList.addAll(cityModels)

            }
        }
        return cityModelList
    }
}
