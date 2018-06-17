package com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Remote.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

class WeatherGeneralData : Parcelable {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("main")
    lateinit var main: String
        internal set
    @SerializedName("description")
    lateinit var description: String
        internal set
    @SerializedName("icon")
    lateinit var icon: String
        internal set


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(this.id)
        dest.writeString(this.main)
        dest.writeString(this.description)
        dest.writeString(this.icon)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.id = `in`.readInt()
        this.main = `in`.readString()
        this.description = `in`.readString()
        this.icon = `in`.readString()
    }

    companion object {
        val CREATOR: Parcelable.Creator<WeatherGeneralData> = object : Parcelable.Creator<WeatherGeneralData> {
            override fun createFromParcel(source: Parcel): WeatherGeneralData {
                return WeatherGeneralData(source)
            }

            override fun newArray(size: Int): Array<WeatherGeneralData?> {
                return arrayOfNulls(size)
            }
        }
    }
}
