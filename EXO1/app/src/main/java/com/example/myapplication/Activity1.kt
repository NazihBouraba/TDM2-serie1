package com.example.myapplication

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_attivity1.*
import java.io.FileNotFoundException
import java.io.IOException

class Activity1 : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attivity1)
        val params = getSharedPreferences("Activity1", 0)
        val couleur = params.getInt("couleur", activity1.solidColor)
        activity1.setBackgroundColor(couleur)
        AppTools.showToast(this, "Données lues")

    }






}
