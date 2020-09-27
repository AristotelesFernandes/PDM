package layout

import android.os.Parcel
import android.os.Parcelable

class CorPreferida (var nome: String, var codigo: String): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun toString(): String {
        return "${this.nome} - ${this.codigo}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nome)
        parcel.writeString(codigo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CorPreferida> {
        override fun createFromParcel(parcel: Parcel): CorPreferida {
            return CorPreferida(parcel)
        }

        override fun newArray(size: Int): Array<CorPreferida?> {
            return arrayOfNulls(size)
        }
    }

}