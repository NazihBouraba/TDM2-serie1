package com.example.exo2

import android.os.Parcel
import android.os.Parcelable
import java.util.*
import java.io.Serializable
import kotlinx.serialization.json.JSON


class Intervention(numero : Int , date : Calendar, NomPlombier : String , type : String):  Serializable {

    var numero = numero
    var date = date
    var NomPlombier = NomPlombier
    var type = type

}