package com.mahmoud.mohammed.qwesysandroidtask

import com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Callbacks
import com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Local.LocalDataRepository
import com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Local.LocalRepositorySource
import com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Local.Model.CityModel

import org.junit.Before
import org.junit.Test

class DataRepositoryTest {
    internal lateinit var mDateRepositoryUnderTesting: LocalRepositorySource

    @Before
    internal fun initRepoUnderTesting() {
        mDateRepositoryUnderTesting = LocalDataRepository()
    }


    @Test
    internal fun getCitiesFail() {

        mDateRepositoryUnderTesting.getCities(object : Callbacks.GetCitiesCallbacks {
            override fun onSuccess(result: List<CityModel>) {
                assert(false)

            }

            override fun onError(error: String) {
                assert(!error.isEmpty())

            }
        })
    }

    @Test
    internal fun getCitiesSuccess() {
        mDateRepositoryUnderTesting.getCities(object : Callbacks.GetCitiesCallbacks {
            override fun onSuccess(result: List<CityModel>) {
                assert(!result.isEmpty())
            }

            override fun onError(error: String) {
                assert(false)

            }
        })
    }


}
