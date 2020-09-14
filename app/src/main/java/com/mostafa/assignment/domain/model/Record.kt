package com.mostafa.assignment.domain.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


@Entity
data class Record(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") var id: Int,
    @SerializedName("volume_of_mobile_data") var volumeMobileData: String,
    @SerializedName("quarter") var quarter: String,
    @SerializedName("_id") var itemId: String,
    var decrease : Int
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readInt(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(volumeMobileData)
        writeString(quarter)
        writeString(itemId)
        writeInt(decrease)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Record> = object : Parcelable.Creator<Record> {
            override fun createFromParcel(source: Parcel): Record = Record(source)
            override fun newArray(size: Int): Array<Record?> = arrayOfNulls(size)
        }
    }
}