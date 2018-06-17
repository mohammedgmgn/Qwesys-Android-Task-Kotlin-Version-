package com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Local.Model

import android.os.Parcel
import android.os.Parcelable

import com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Local.RealmQueries

import java.util.UUID

import io.realm.Realm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

public open class CityModel : RealmObject, Parcelable {
    @PrimaryKey
    var cityId = UUID.randomUUID().toString()
    var cityName: String? = null

    constructor(cityName: String) {
        this.cityName = cityName
    }

    constructor() {}

    fun saveCityInLocalDb() {

        RealmQueries.saveCityData(this)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.cityId)
        dest.writeString(this.cityName)
    }


    protected constructor(`in`: Parcel) {
        this.cityId = `in`.readString()
        this.cityName = `in`.readString()
    }
    companion object CREATOR : Parcelable.Creator<CityModel> {
        override fun createFromParcel(source: Parcel): CityModel {
            return CityModel(source)

        }

        override fun newArray(size: Int): Array<CityModel?> {
            return arrayOfNulls(size)
        }


        }

    /*companion object {

        val CREATOR: Parcelable.Creator<CityModel> = object : Parcelable.Creator<CityModel> {
            override fun createFromParcel(source: Parcel): CityModel {
                return CityModel(source)
            }

            override fun newArray(size: Int): Array<CityModel?> {
                return arrayOfNulls(size)
            }
        }
    }*/
}
