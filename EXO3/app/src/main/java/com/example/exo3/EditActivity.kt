package com.example.exo3

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.intervention_item.*

class EditActivity : AppCompatActivity() {

    var mode: String = ""
    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        mode = intent.getStringExtra("mode")
        if(mode == "modif"){
            val numero = intent.getIntExtra("numero",-1)
            chargerProduit(numero)

        }
       btnSauvegarder.setOnClickListener({
           this.sauvegarderProduit()
        })
    }


    fun chargerProduit(code: Int)  {
        var act = this


        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                val db = InterventionDB.getInstance(act)
                val dao = db?.InterventionDAO()
                val produit = dao?.getProduit(code)
                if (produit != null) {


                    var a = produit[0].numero.toString()
                    a= produit[0].date.toString()
                    a = produit[0].type.toString()
                    a= produit[0].npm_plombier.toString()
                    txtn.setText(produit[0].numero.toString())
                    txtd.setText(produit[0].date.toString())
                    txtt.setText(produit[0].type.toString())
                    txtp.setText(produit[0].npm_plombier.toString())
                }


                return null
            }

            override fun onPostExecute(result: Void?) {

            }
        }.execute()
    }


    fun sauvegarderProduit() {
        var act = this

        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                val db = InterventionDB.getInstance(act)
                val dao = db?.InterventionDAO()

                if(mode == "modif"){


                    val numero = txtn.text.toString().toInt()
                    val date = txtd.text.toString()
                    val nom = txtp.text.toString()
                    val type = txtt.text.toString()
                    val p = InterventionEntity(numero,date,nom,type)
                    dao?.modifier(p)
                }


                else{
                    val numero = txtn.text.toString().toInt()
                    val date = txtd.text.toString()
                    val nom = txtp.text.toString()
                    val type = txtt.text.toString()
                    val produit = InterventionEntity(numero,date,nom,type)
                    dao?.ajouter(produit)
                }


                return null
            }


            override fun onPostExecute(result: Void?) {
               Toast.makeText(context,"donnée modifiée",Toast.LENGTH_SHORT).show()
                returnToList()

            }
        }.execute()
    }
    fun returnToList(){
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
