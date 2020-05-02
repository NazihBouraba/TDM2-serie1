package com.example.exo3

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_database.*

class MainActivity : AppCompatActivity() {

    private var db: InterventionDB? = null
    private var dao: InterventionDAO? = null
    private var list_intervention: MutableList<InterventionEntity>? = null


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
                act.db = InterventionDB.getInstance(act)
                act.dao = db?.InterventionDAO()
                list_intervention = act.dao?.getProduits()


                return null
            }

            override fun onPostExecute(result: Void?) {
                if(list_intervention != null) {
                    val adapter = InterventionListAdapter(act, R.layout.intervention_item, list_intervention!!)
                    lvProduits.adapter = adapter
                   Toast.makeText(act,"données chargeés",Toast.LENGTH_SHORT).show()
                }

            }
        }.execute()
    }
}
