package com.example.exo3

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class InterventionEntity(
    @PrimaryKey() var numero: Int,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "nom plombier") var npm_plombier: String,
    @ColumnInfo(name = "type intervention") var type: String
){
    constructor():this(0,"","",""){

    }
}