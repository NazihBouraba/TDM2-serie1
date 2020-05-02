package com.example.exo6

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class Seance(

    @PrimaryKey() var id: Int,
    @ColumnInfo(name = "jour") var jour:String,
    @ColumnInfo(name = "heured") var heured:String,
    @ColumnInfo(name = "heuref") var heuref:String,
    @ColumnInfo(name = "module") var module:String,
    @ColumnInfo(name = "salle") var salle:String,
    @ColumnInfo(name = "enseignant") var enseignant : String,
    @ColumnInfo(name = "annee") var annee:String,
    @ColumnInfo(name = "groupe") var groupe : String) {


}