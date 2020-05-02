package com.example.exo6

import androidx.room.*


@Dao
interface SeanceDAO {
    @Query("SELECT * FROM Seance")
    fun getseances(): MutableList<Seance>

    @Query("SELECT * FROM Seance WHERE annee = :date")
    fun getseanceannee(date: String): List<Seance>

    @Query("SELECT * FROM Seance WHERE groupe = :g")
    fun getseancegroupe(g: String): List<Seance>

    @Insert
    fun ajouter(s : Seance)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun modifier(s:  Seance)

    @Delete
    fun supprimer(s: Seance)
}