package com.example.exo6

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import java.io.File
import java.io.FileWriter
import java.io.Reader
import java.io.Writer
import java.nio.file.Files

import java.nio.file.Paths


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        writejsonfile()
      var  ListeSeance =  readjsonfile()
        val a = ListeSeance?.size
        for (m in ListeSeance!!) {
            Log.v("sdsd",m?.id?.toString())
        }
   }



    @RequiresApi(Build.VERSION_CODES.O)
    fun readjsonfile(): List<Seance>?{

        val file = File(getExternalFilesDir(null), "seance.txt")
        try {
            // create Gson instance
            val gson = Gson()

            // create a reader
            val reader: Reader = Files.newBufferedReader(Paths.get(file.path))

            // convert JSON file to map
            val map: List<*> =
                gson.fromJson(reader, List::class.java)
            var   liste = map as List<Seance>
            return liste




            // close reader
            reader.close()
        } catch (ex: java.lang.Exception) {
            ex.printStackTrace()
            return null
        }

    }


    fun writejsonfile(){

        val file = File(getExternalFilesDir(null), "seance.txt")
        try {
            // create a map


            val map= ArrayList<Seance>()
           var s = Seance(1,"12","10","12","tdm","s11","enseigant1","2020","g1")
            map.add(s)
             s = Seance(2,"10","8","10","alog","s4","enseigant2","2020","g2")
            map.add(s)
            s = Seance(3,"23","13","15","bda","m3","enseigant3","2020","g3")
            map.add(s)
            // create a writer

            val writer = FileWriter(file.path)

            // convert map to JSON File
            Gson().toJson(map, writer)
            // close the writer
            writer.close()


        } catch (ex: Exception) {
            ex.printStackTrace()

        }

    }
}
