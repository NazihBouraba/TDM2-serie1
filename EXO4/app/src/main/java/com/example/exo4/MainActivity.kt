package com.example.exo4

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_database.*

class MainActivity : AppCompatActivity() {


    private var db: NoteDB? = null
    private var dao: NoteDAO? = null
    private var list_notes: MutableList<Note>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initDB()
        val activity = this

        fab.setOnClickListener {
            val intent = Intent(activity, EditActivity::class.java)
            intent.putExtra("mode","ajout")
            startActivity(intent)
        }
    }



    fun initDB() {
        var act = this


        object : AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg voids: Void): Void? {
                act.db = NoteDB.getInstance(act)
                act.dao = db?.NoteDAO()
                list_notes = act.dao?.getProduits()


                return null
            }

            override fun onPostExecute(result: Void?) {
                if(list_notes != null) {
                    val adapter = NoteListAdapter(act, R.layout.note_item, list_notes!!)
                    lvnote.adapter = adapter
                    Toast.makeText(act,"données chargeés",Toast.LENGTH_SHORT).show()
                }

            }
        }.execute()
    }
}
