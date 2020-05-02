package com.example.myapplication

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_3.*
import kotlinx.android.synthetic.main.activity_attivity1.*

class Activity3 : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)
        val params = getSharedPreferences("Activity3", 0)
        val couleur = params.getInt("couleur", activity3.solidColor)
        activity3.setBackgroundColor(couleur)
        AppTools.showToast(this, "Données lues")
    }
}
