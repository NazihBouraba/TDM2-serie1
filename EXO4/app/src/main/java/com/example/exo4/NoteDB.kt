package com.example.exo4

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Note::class), version = 1)
abstract class NoteDB(): RoomDatabase() {

    abstract fun NoteDAO():NoteDAO

    companion object {
        private var instance: NoteDB? = null

        fun getInstance(context: Context): NoteDB? {
            if (instance == null) {


                instance = Room.databaseBuilder(context.getApplicationContext(),
                        NoteDB::class.java, "note.db")
                    .build()


            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }

}