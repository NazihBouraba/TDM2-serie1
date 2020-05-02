package com.example.exo3

import android.app.DatePickerDialog
import android.widget.DatePicker
import java.text.SimpleDateFormat
import java.util.*

object DateManager {


    var cal = Calendar.getInstance()

    val dateSetListener = object : DatePickerDialog.OnDateSetListener {
        override fun onDateSet(
            view: DatePicker, year: Int, monthOfYear: Int,
            dayOfMonth: Int
        ) {
             cal = Calendar.getInstance()
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateInView()
        }
    }

    fun afficherdate(date: Calendar):String{

        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        return sdf.format(date.getTime())

    }

    private fun updateDateInView(): String {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)

        return afficherdate(cal)
    }
}