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
import com.google.firebase.firestore.firestore

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

                var firestore = Firebase.firestore

                if(firestore.collection("Users").document(it.user!!.uid).get().result.exists()){
                    if(firestore.collection("Users")
                        .document(it.user!!.uid).get().result.get("status")==0) {
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {

                    }
                } else {
                    // for the first time this no is registered


                    /*var userMap = mapOf<String, Any>(
                        "name" to name,
                        "phnNo" to phnNo,
                        "email" to email,
                        "pass" to pass,
                        "dob" to dob,
                        "gender" to gender
                    )

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


                    startActivity(Intent(this, UserProfile::class.java))
*/                }


            }.addOnFailureListener {
                Log.d("Failure","Can't Log-in ${it.message}")
                it.printStackTrace()
            }
        }
    }
}