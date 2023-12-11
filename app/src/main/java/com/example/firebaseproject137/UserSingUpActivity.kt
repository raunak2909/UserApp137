package com.example.firebaseproject137

import android.R
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.firebaseproject137.databinding.ActivityUserSingUpBinding
import java.text.SimpleDateFormat
import java.util.Calendar

class UserSingUpActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserSingUpBinding
    val arrIds = ArrayList<String>()
    var dateFormate = SimpleDateFormat("dd/MM/YYYY")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserSingUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //for Date Start
        binding.selectDate.setOnClickListener {

            val getDate = Calendar.getInstance()

            val datepicker = DatePickerDialog(this, R.style.Theme_Holo_Dialog_NoActionBar_MinWidth, DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->

                val selectDate = Calendar.getInstance()
                selectDate.set(Calendar.YEAR, i)
                selectDate.set(Calendar.MONTH, i2)
                selectDate.set(Calendar.DAY_OF_MONTH, i3)

                val date = dateFormate.format(selectDate.time)
                Toast.makeText(this, "Date : " + date, Toast.LENGTH_SHORT).show()
                binding.selectDate.text = date

            },getDate.get(Calendar.YEAR), getDate.get(Calendar.MONTH), getDate.get(Calendar.DAY_OF_MONTH)   )
            datepicker.show()
        }

        //Date Close


        //Spinner State Select Start
        arrIds .apply {

            add("Select State")
            add("West Bengal")
            add("Rajasthan")
            add("Orissa")
            add("Madhya Pradesh")
        }


        val mAdapter = ArrayAdapter<String>(this, R.layout.simple_spinner_dropdown_item, arrIds )

        binding.spinnerID.adapter = mAdapter

        //Spinner State Select Close
    }
}