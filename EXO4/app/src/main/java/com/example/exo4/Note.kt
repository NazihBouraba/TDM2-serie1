package com.example.exo4

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey() var id: Int,
    @ColumnInfo(name = "titre") var titre: String,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "couleur") var couleur: Int,
    @ColumnInfo(name = "text") var contenu: String

) {

    constructor():this(0,"","",1111,""){

    }

}