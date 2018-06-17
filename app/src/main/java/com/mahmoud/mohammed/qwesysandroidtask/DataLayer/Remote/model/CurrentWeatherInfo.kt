package com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Remote.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

class CurrentWeatherInfo : Parcelable {

    @SerializedName("main")
    lateinit var temperature: TemperatureInfoData
    @SerializedName("weather")
    internal lateinit var weatherGeneralData: List<WeatherGeneralData>
    @SerializedName("name")
    lateinit var cityName: String
        internal set

    constructor() {}

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeParcelable(this.temperature, flags)
        dest.writeTypedList(this.weatherGeneralData)
        dest.writeString(this.cityName)
    }

    protected constructor(`in`: Parcel) {
        this.temperature = `in`.readParcelable(TemperatureInfoData::class.java.classLoader)
        this.weatherGeneralData = `in`.createTypedArrayList(WeatherGeneralData.CREATOR)
        this.cityName = `in`.readString()
    }

    companion object {

        val CREATOR: Parcelable.Creator<CurrentWeatherInfo> = object : Parcelable.Creator<CurrentWeatherInfo> {
            override fun createFromParcel(source: Parcel): CurrentWeatherInfo {
                return CurrentWeatherInfo(source)
            }

            override fun newArray(size: Int): Array<CurrentWeatherInfo?> {
                return arrayOfNulls(size)
            }
        }
    }
}
