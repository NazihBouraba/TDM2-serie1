package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import kotlinx.android.synthetic.main.activity_2.*
import kotlinx.android.synthetic.main.activity_3.*
import kotlinx.android.synthetic.main.activity_attivity1.*

import kotlinx.android.synthetic.main.settings_activity.*
import java.io.FileNotFoundException
import java.io.IOException


class SettingsActivity : AppCompatActivity() , View.OnClickListener{


    lateinit var act1 : SharedPreferences
    lateinit var act2 : SharedPreferences
    lateinit var act3 : SharedPreferences

    var color =0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.settings_activity)
          act1 = getSharedPreferences("Activity1", 0)
          act2 = getSharedPreferences("Activity2", 0)
          act3 = getSharedPreferences("Activity3", 0)
        activity1bt.setOnClickListener(this)
        activity2bt.setOnClickListener(this)
        activity3bt.setOnClickListener(this)


    }


    override fun onClick(v: View?) {
        when(v?.id){

            R.id.activity1bt->{
                ColorPickerDialogBuilder
                    .with(this)
                    .setTitle("Choose color")
                    .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                    .density(12)
                    .setOnColorSelectedListener { selectedColor ->

                            EcrireDansLaZonePartagee(act1, selectedColor)

                    }
                    .setPositiveButton(
                        "ok"
                    ) { dialog, selectedColor, allColors ->  }
                    .setNegativeButton(
                        "cancel"
                    ) { dialog, which -> }
                    .build()
                    .show()

            }

            R.id.activity2bt->{
                ColorPickerDialogBuilder
                    .with(this)
                    .setTitle("Choose color")
                    .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                    .density(12)
                    .setOnColorSelectedListener { selectedColor ->
                        EcrireDansLaZonePartagee(act2, selectedColor)

                    }
                    .setPositiveButton(
                        "ok"
                    ) { dialog, selectedColor, allColors ->  }
                    .setNegativeButton(
                        "cancel"
                    ) { dialog, which -> }
                    .build()
                    .show()

            }
            R.id.activity3bt->{
                ColorPickerDialogBuilder
                    .with(this)
                    .setTitle("Choose color")
                    .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                    .density(12)
                    .setOnColorSelectedListener { selectedColor ->
                        EcrireDansLaZonePartagee(act3, selectedColor)

                    }
                    .setPositiveButton(
                        "ok"
                    ) { dialog, selectedColor, allColors ->   }
                    .setNegativeButton(
                        "cancel"
                    ) { dialog, which -> }
                    .build()
                    .show()

            }




        }
    }



    fun EcrireDansLaZonePartagee(p:SharedPreferences,c:Int){
        val editeur =p.edit()
        editeur.putInt("couleur",c)
        editeur.apply()
        editeur.commit()
        AppTools.showToast(this, "Données sauvegardées")
    }

}


