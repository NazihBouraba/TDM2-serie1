package com.example.exo6

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Seance::class), version = 1)
abstract class SeanceDB(): RoomDatabase() {

    abstract fun NoteDAO():SeanceDAO

    companion object {
        private var instance: SeanceDB? = null

        fun getInstance(context: Context): SeanceDB? {
            if (instance == null) {


                instance = Room.databaseBuilder(context.getApplicationContext(),
                        SeanceDB::class.java, "note.db")
                    .build()


            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }

}