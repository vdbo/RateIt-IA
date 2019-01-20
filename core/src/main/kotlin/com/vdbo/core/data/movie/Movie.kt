package com.vdbo.core.data.movie

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "rating") val rating: Float
) : Parcelable {

    companion object CREATOR : Parcelable.Creator<Movie> {

        override fun createFromParcel(parcel: Parcel) = Movie(parcel)

        override fun newArray(size: Int): Array<Movie?> = arrayOfNulls(size)

    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readFloat()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeFloat(rating)
    }

    override fun describeContents() = 0

}