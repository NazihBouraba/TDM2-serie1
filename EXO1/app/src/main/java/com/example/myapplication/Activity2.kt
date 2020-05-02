package com.example.myapplication

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_2.*
import kotlinx.android.synthetic.main.activity_3.*
import kotlinx.android.synthetic.main.activity_attivity1.*

class Activity2 : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        val params = getSharedPreferences("Activity2", 0)
        val couleur = params.getInt("couleur", activity2.solidColor)
        activity2.setBackgroundColor(couleur)
        AppTools.showToast(this, "Données lues")
    }
}
