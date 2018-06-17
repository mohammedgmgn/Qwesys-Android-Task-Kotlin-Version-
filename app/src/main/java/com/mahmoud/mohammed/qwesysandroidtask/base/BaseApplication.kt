package com.mahmoud.mohammed.qwesysandroidtask.base

import android.app.Application

import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger

import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmMigration

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)
        initRealm()
    }

    private fun initRealm() {

        Realm.init(this)
        val config = RealmConfiguration.Builder().name(NAME).build()
        Realm.setDefaultConfiguration(config)
    }

    companion object {
        val NAME = "QwesysApp"
    }

}