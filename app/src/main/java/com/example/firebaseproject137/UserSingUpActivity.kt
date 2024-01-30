package com.example.firebaseproject137

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.firebaseproject137.databinding.ActivityUserSingUpBinding
import com.google.firebase.Firebase
import com.google.firebase.FirebaseOptions
import com.google.firebase.app
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.initialize
import java.text.SimpleDateFormat
import java.util.Calendar

class UserSingUpActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserSingUpBinding
    val arrIds = ArrayList<String>()
    lateinit var mAuth: FirebaseAuth
    var gender = "male"
    var dob = ""
    var dateFormate = SimpleDateFormat("dd/MM/YYYY")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserSingUpBinding.inflate(layoutInflater)
        setContentView(binding.root)




        mAuth = Firebase.auth

        //for Date Start
        binding.selectDate.setOnClickListener {

            val getDate = Calendar.getInstance()

            val datepicker = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->

                    val selectDate = Calendar.getInstance()
                    selectDate.set(Calendar.YEAR, i)
                    selectDate.set(Calendar.MONTH, i2)
                    selectDate.set(Calendar.DAY_OF_MONTH, i3)

                    val date = dateFormate.format(selectDate.time)
                    Toast.makeText(this, "Date : " + date, Toast.LENGTH_SHORT).show()
                    binding.selectDate.text = date
                    dob = date

                },
                getDate.get(Calendar.YEAR),
                getDate.get(Calendar.MONTH),
                getDate.get(Calendar.DAY_OF_MONTH)
            )
            datepicker.show()
        }

        //Date Close


        //Spinner State Select Start
        arrIds.apply {

            add("Select State")
            add("West Bengal")
            add("Rajasthan")
            add("Orissa")
            add("Madhya Pradesh")
        }


        val mAdapter = ArrayAdapter<String>(
            this,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            arrIds
        )

        binding.spinnerID.adapter = mAdapter

        //Spinner State Select Close


        binding.radioGrpGender.setOnCheckedChangeListener { radioGroup, i ->
            gender = if (R.id.radio_male == i) {
                "Male"
            } else if (R.id.radio_female == i) {
                "Female"
            } else {
                "Other"
            }
        }

        binding.btnSubmit.setOnClickListener {
            var name = binding.editFullName.text.toString()
            var phnNo = binding.phoneEditText.text.toString()
            var email = binding.editEmail.text.toString()

            var pass = binding.editPass.text.toString()

            var firestore = Firebase.firestore


            var userMap = mapOf<String, Any>(
                "name" to name,
                "phnNo" to phnNo,
                "email" to email,
                "pass" to pass,
                "dob" to dob,
                "gender" to gender,
                "status" to 2,
            )

            // 0-> Active,
            // 1-> InActive,
            // 2-> Pending,
            // 3-> Block

            mAuth
                .createUserWithEmailAndPassword(email, pass)
                .addOnSuccessListener {
                    Log.d("uid", "${it.user!!.uid}")


                    firestore
                        .collection("Users")
                        .document("${it.user!!.uid}")
                        .set(userMap)
                        .addOnSuccessListener {
                            Log.d("Success", "User Added!!")
                            startActivity(Intent(this@UserSingUpActivity, UserLoginActivity::class.java))
                        }.addOnFailureListener {
                            Log.d("User Add Failure", "User not Added ${it.message}!!")
                            it.printStackTrace()
                        }


                }.addOnFailureListener {
                    Log.d("Create User Failure", "User not Added ${it.message}!!")
                    it.printStackTrace()
                }

        }
    }
}