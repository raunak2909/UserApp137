package com.example.firebaseproject137

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.firebaseproject137.databinding.ActivityOtpactivityBinding
import com.example.firebaseproject137.databinding.ActivityUserSingUpBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.auth

class OTPActivity : AppCompatActivity() {

    lateinit var binding: ActivityOtpactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val vid = intent.getStringExtra("vid")

            val smsCode = binding.edtOTP.text.toString()
            val credential = PhoneAuthProvider.getCredential(vid!!, smsCode)

            Firebase.auth.signInWithCredential(credential).addOnSuccessListener {
                Log.d("Success","Logged in successfully..${it.user!!.uid}")

                //sharedPref
                val pref = getSharedPreferences("login", MODE_PRIVATE);
                pref.edit().putString("uid", it.user!!.uid).apply()

                startActivity(Intent(this, MainActivity::class.java))
            }.addOnFailureListener {
                Log.d("Failure","Can't Log-in ${it.message}")
                it.printStackTrace()
            }
        }
    }
}