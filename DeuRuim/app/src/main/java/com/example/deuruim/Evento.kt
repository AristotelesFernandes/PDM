package com.example.deuruim

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable

class Evento (var descricao: String, var foto: Bitmap?, var nota: Int): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readParcelable(Bitmap::class.java.classLoader),
        parcel.readInt()
    ) {
    }

    override fun toString(): String {
        return "${this.descricao} - ${this.nota}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(descricao)
        parcel.writeParcelable(foto, flags)
        parcel.writeInt(nota)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Evento> {
        override fun createFromParcel(parcel: Parcel): Evento {
            return Evento(parcel)
        }

        override fun newArray(size: Int): Array<Evento?> {
            return arrayOfNulls(size)
        }
    }


}