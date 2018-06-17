package com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Remote.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

class TemperatureInfoData : Parcelable {

    @SerializedName("temp")
    var temp: Float = 0.toFloat()
    @SerializedName("pressure")
    var pressure: Float = 0.toFloat()
    @SerializedName("humidity")
    var humidity: Float = 0.toFloat()
    @SerializedName("temp_min")
    var temp_min: Float = 0.toFloat()
    @SerializedName("temp_max")
    var temp_max: Float = 0.toFloat()

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeFloat(this.temp)
        dest.writeFloat(this.pressure)
        dest.writeFloat(this.humidity)
        dest.writeFloat(this.temp_min)
        dest.writeFloat(this.temp_max)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.temp = `in`.readFloat()
        this.pressure = `in`.readFloat()
        this.humidity = `in`.readFloat()
        this.temp_min = `in`.readFloat()
        this.temp_max = `in`.readFloat()
    }

    companion object {

        val CREATOR: Parcelable.Creator<TemperatureInfoData> = object : Parcelable.Creator<TemperatureInfoData> {
            override fun createFromParcel(source: Parcel): TemperatureInfoData {
                return TemperatureInfoData(source)
            }

            override fun newArray(size: Int): Array<TemperatureInfoData?> {
                return arrayOfNulls(size)
            }
        }
    }
}
