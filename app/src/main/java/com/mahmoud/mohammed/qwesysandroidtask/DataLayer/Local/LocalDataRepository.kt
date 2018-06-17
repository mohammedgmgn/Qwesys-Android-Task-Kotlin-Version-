package com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Local

import com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Callbacks

class LocalDataRepository : LocalRepositorySource {
    override fun getCities(callbacks: Callbacks.GetCitiesCallbacks) {
        callbacks.onSuccess(RealmQueries.getCitiesList())
}}
