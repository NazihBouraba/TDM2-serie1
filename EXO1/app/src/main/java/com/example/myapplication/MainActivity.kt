package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        a1.setOnClickListener(this)
        a2.setOnClickListener(this)
        a3.setOnClickListener(this)
        s.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){

                R.id.a1->{
                    var intent = Intent()
                    intent.setClass(this, Activity1::class.java)
                    startActivity(intent)

                }

                R.id.a2->{
                    var intent = Intent()
                    intent.setClass(this, Activity2::class.java)
                    startActivity(intent)

                }
                R.id.a3->{
                    var intent = Intent()
                    intent.setClass(this, Activity3::class.java)
                    startActivity(intent)

                }
                R.id.s->{
                    var intent = Intent()
                    intent.setClass(this, SettingsActivity::class.java)
                    startActivity(intent)

                }



            }
        }
    }
}
