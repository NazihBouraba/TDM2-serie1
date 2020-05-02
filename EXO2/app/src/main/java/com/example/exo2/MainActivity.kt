package com.example.exo2

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_modify.*
import java.io.FileInputStream
import java.io.ObjectInputStream
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), View.OnClickListener {


    lateinit var liste_intervention : ArrayList<Intervention>
    var cal = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         liste_intervention = lirefichier()
        var interventionadapter = InterventionAdapter(liste_intervention,this)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = interventionadapter
        recyclerview.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        button.setOnClickListener(this)
        textView2.text = afficherdate(cal)
        change_date.setOnClickListener(this)
        switch1.setOnClickListener(){
            if(switch1.isChecked)
            {
                var Liste = ArrayList<Intervention>()
                for (i in liste_intervention)
                {
                    if(afficherdate(i.date).compareTo(afficherdate(cal))==0)
                    {Liste.add(i)

                    }

                }
                recyclerview.swapAdapter(InterventionAdapter(Liste,this),false)
            }else{

                recyclerview.swapAdapter(InterventionAdapter(liste_intervention,this),false)
            }





        }

    }

    fun lirefichier() : ArrayList<Intervention>{
        val fileName = "intervention.txt"
        val fis: FileInputStream = openFileInput(fileName)
        var iss = ObjectInputStream(fis)
        val interventions: ArrayList<Intervention> = iss.readObject() as ArrayList<Intervention>
        iss.close()
        fis.close()

        return interventions
    }


    override fun onClick(v: View?) {
        when(v?.id){

            R.id.button->{

                var intent = Intent(this,Ajout::class.java)
                var bundle = Bundle()
                bundle.putSerializable("Liste", liste_intervention as Serializable? )
                intent.putExtras(bundle)
                startActivity(intent)

            }

            R.id.change_date->{

                DatePickerDialog(
                    this@MainActivity,
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

        textView2.text = afficherdate(cal)
    }
}
