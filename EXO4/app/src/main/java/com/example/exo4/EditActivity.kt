package com.example.exo4

import android.app.DatePickerDialog
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.exo4.DateManager.afficherdate
import com.example.exo4.DateManager.dateSetListener
import com.example.exo4.DateManager.updateDateInView
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import kotlinx.android.synthetic.main.activity_edit.*
import java.util.*

class EditActivity : AppCompatActivity() {

    var cal = Calendar.getInstance()
    var mode: String = ""
    var noteid=0
    val context = this
    var color = 0
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
        btncahngercouleur.setOnClickListener({
            ColorPickerDialogBuilder
                .with(this)
                .setTitle("Choose color")
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener { selectedColor ->
                    changercouleur(selectedColor)

                }
                .setPositiveButton(
                    "ok"
                ) { dialog, selectedColor, allColors ->  }
                .setNegativeButton(
                    "cancel"
                ) { dialog, which -> }
                .build()
                .show()

        })

        btndate.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(
                    this@EditActivity,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
                date_edit.text = afficherdate(cal)
            }

        })
    }


    fun chargerProduit(code: Int)  {
        var act = this


        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                val db = NoteDB.getInstance(act)
                val dao = db?.NoteDAO()
                val produit = dao?.getProduit(code)
                if (produit != null) {



                    noteid = produit[0].id
                    titre_et.setText(produit[0].titre)
                    date_edit.setText(produit[0].date)
                    contenu_et.setText(produit[0].contenu)
                    monLayout.setBackgroundColor(produit[0].couleur)
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
                val db = NoteDB.getInstance(act)
                val dao = db?.NoteDAO()

                if(mode == "modif"){


                    val titre = titre_et.text.toString()
                    val date = afficherdate(cal)
                    val contenu = contenu_et.text.toString()

                    val p = Note(noteid,titre,date,color, contenu)
                    dao?.modifier(p)
                }


                else{
                    val titre = titre_et.text.toString()
                    val date = date_edit.text.toString()
                    val contenu = contenu_et.text.toString()

                    val p = Note(noteid,titre,date,color, contenu)

                    dao?.ajouter(p)
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

    fun changercouleur(c : Int)
    {
        color = c
        monLayout.setBackgroundColor(c)
    }
}
