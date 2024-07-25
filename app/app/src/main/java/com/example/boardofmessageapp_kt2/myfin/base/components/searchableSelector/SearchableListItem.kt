package com.example.boardofmessageapp_kt2.base.components.searchableSelector

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep


@Keep
data class SearchableListItem(val id: String, val name: String, val description: String? = null) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SearchableListItem> {
        override fun createFromParcel(parcel: Parcel): SearchableListItem {
            return SearchableListItem(parcel)
        }

        override fun newArray(size: Int): Array<SearchableListItem?> {
            return arrayOfNulls(size)
        }
    }
}