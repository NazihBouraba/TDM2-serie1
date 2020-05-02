package com.example.exo4

import androidx.room.*


@Dao
interface NoteDAO {
    @Query("SELECT * FROM Note")
    fun getProduits(): MutableList<Note>

    @Query("SELECT * FROM Note WHERE id = :num")
    fun getProduit(num: Int): List<Note>

    @Insert
    fun ajouter(intervention : Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun modifier(intervention:  Note)

    @Delete
    fun supprimer(produit: Note)
}