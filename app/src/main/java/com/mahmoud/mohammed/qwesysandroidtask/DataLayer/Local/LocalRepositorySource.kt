package com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Local

import com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Callbacks

interface LocalRepositorySource {
    fun getCities(callbacks: Callbacks.GetCitiesCallbacks)

}
