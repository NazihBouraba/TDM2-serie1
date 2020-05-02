package com.example.exo3

import androidx.room.*

@Dao
interface InterventionDAO {

    @Query("SELECT * FROM InterventionEntity")
    fun getProduits(): MutableList<InterventionEntity>

    @Query("SELECT * FROM InterventionEntity WHERE numero = :num")
    fun getProduit(num: Int): List<InterventionEntity>

    @Insert
    fun ajouter(intervention : InterventionEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun modifier(intervention:  InterventionEntity)

    @Delete
    fun supprimer(produit: InterventionEntity)
}