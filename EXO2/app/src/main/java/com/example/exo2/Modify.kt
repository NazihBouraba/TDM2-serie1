package com.example.exo2

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_modify.*
import java.io.File
import java.io.FileOutputStream
import java.io.ObjectOutputStream
import java.io.PrintWriter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Modify : AppCompatActivity() , View.OnClickListener {
    var cal = Calendar.getInstance()
    lateinit  var interventions : ArrayList<Intervention>
     var position =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify)

         interventions = intent.getSerializableExtra("Liste") as ArrayList<Intervention>
         position = intent.getIntExtra("position",-1)

        numero_et.setText( interventions[position].numero.toString())
        modifier_date_tv.text = afficherdate( interventions[position].date)
        nom_plombier_et.setText(interventions[position].NomPlombier)
        type_et.setText(interventions[position].type.toString())
        date_bt.setOnClickListener(this)
        save.setOnClickListener(this)
        supprimer.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){

                R.id.date_bt->{
                    DatePickerDialog(
                        this@Modify,
                        dateSetListener,
                        // set DatePickerDialog to point to today's date when it loads up
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)
                    ).show()



                }

                R.id.save->{
                    interventions[position].numero =  numero_et.text.toString().toInt()
                    interventions[position].date = cal
                    interventions[position].NomPlombier = nom_plombier_et.text.toString()
                    interventions[position].type = type_et.text.toString()
                    writetofile()
                }

                R.id.supprimer->{
                    interventions.remove(interventions[position])
                    Toast.makeText(this,"nombre d element"+position,Toast.LENGTH_SHORT).show()
                    var f =File("intervention.txt")
                    f.delete()
                    writetofile()

                }


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

     modifier_date_tv.text = afficherdate(cal)
    }


    fun writetofile(){



        val fileName = "intervention.txt"
        val fos: FileOutputStream = openFileOutput(fileName, MODE_PRIVATE)
        val os = ObjectOutputStream(fos)
        os.writeObject(interventions)
        os.close()
        fos.close()


    }



}
