package com.example.exo2

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_ajout.*
import kotlinx.android.synthetic.main.activity_modify.*
import java.io.FileOutputStream
import java.io.ObjectOutputStream
import java.text.SimpleDateFormat
import java.util.*

class Ajout : AppCompatActivity() , View.OnClickListener {

    lateinit var interventions : ArrayList<Intervention>
    var cal = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajout)
        interventions = intent.getSerializableExtra("Liste") as ArrayList<Intervention>
        ajouter.setOnClickListener(this)
        adate_bt.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){

            R.id.ajouter->{
                var i= Intervention(
                    anumero_et.text.toString().toInt(),
                    cal,
                    anom_plombier_et.text.toString(),
                    atype_et.text.toString()
                )

                interventions.add(i)
                writetofile()

            }

            R.id.adate_bt->{

                DatePickerDialog(
                    this@Ajout,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()


            }
        }
    }



    val dateSetListener = object : DatePickerDialog.OnDateSetListener {
        override fun onDateSet(
            view: DatePicker, year: Int, monthOfYear: Int,
            dayOfMonth: Int
        ) {
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateInView()
        }
    }

    fun afficherdate(date:Calendar):String{

        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        return sdf.format(date.getTime())

    }

    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)

        ajouter_date_tv.text = afficherdate(cal)
    }

    fun writetofile(){



        val fileName = "intervention.txt"
        val fos: FileOutputStream = openFileOutput(fileName, MODE_PRIVATE)
        val os = ObjectOutputStream(fos)
        os.writeObject(interventions)
        os.close()
        fos.close()
        Toast.makeText(this,"Données enregistrées", Toast.LENGTH_SHORT).show()

    }
}
