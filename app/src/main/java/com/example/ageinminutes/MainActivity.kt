package com.example.ageinminutes

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.ageinminutes.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        binding.btnDatePicker.setOnClickListener { view -> clickDatePicker(view) }
    }
    private fun clickDatePicker(view: View) {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val dayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH)
        // this code will shod the calendar on my app

        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, selectedDay, selectedMonth, selectedYear ->
                Toast.makeText(
                    this, "the year is $selectedYear ," +
                            "the month is $selectedMonth ," +
                            "the day is $selectedDay", Toast.LENGTH_LONG
                ).show()

                val selectedDate = "$selectedYear/${selectedMonth+1}/$selectedDay"
                binding.tvSelectedDate.setText(selectedDate)
                val sdf = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH) // sdf = simple date format
                val finalDate = sdf.parse(selectedDate)

                // parse methode converted the string (selectedDate) to Date object (Date java class)
                // these codes will show the entered date on the app

                val selectedDateInMinutes = finalDate!!.time/60000
                //time method returns milliseconds
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDate!!.time/60000
                val ageInMinutes = currentDateInMinutes - selectedDateInMinutes
                binding.ageInMinutes.setText(ageInMinutes.toString())

            }, year, month, dayOfMonth
        )
        dpd.datePicker.setMaxDate(Date().time - 86400000 )
        //86400000 is the millieseconds of a day
        // it means you can not access to future date
        dpd.show()
    }
}