package com.example.firebaseproject137

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.firebaseproject137.admin.AdminSplashActivity
import com.example.firebaseproject137.databinding.ActivityUserLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.auth
import io.grpc.util.AdvancedTlsX509TrustManager.Verification
import java.util.concurrent.TimeUnit

class UserLoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserLoginBinding
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = Firebase.auth

        binding.btnSingUp.setOnClickListener {
            startActivity(Intent(this, UserSingUpActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {

            // Email Auth
           /* val email = binding.editUserId.text.toString()
            val pass = binding.editPass.text.toString()

            firebaseAuth
                .signInWithEmailAndPassword(email, pass)
                .addOnSuccessListener {
                    Log.d("Success","Logged in successfully..${it.user!!.uid}")

                    //sharedPref
                    val pref = getSharedPreferences("login", MODE_PRIVATE);
                    pref.edit().putString("uid", it.user!!.uid).apply()

                    startActivity(Intent(this, MainActivity::class.java))
                }.addOnFailureListener {
                    Log.d("Failure","Can't Log-in ${it.message}")
                    it.printStackTrace()
                }*/

            // Mobile OTP Auth

            val options = PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber("+917020797849") // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(this) // Activity (for callback binding)
                .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                    override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                        Log.d("Verification Successful", "${p0.smsCode}")
                    }

                    override fun onVerificationFailed(p0: FirebaseException) {
                        Log.d("Verification Failed!!", "${p0.message}")
                        p0.printStackTrace()
                    }

                    override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                        super.onCodeSent(p0, p1)
                        Log.d("Code Sent!!", "${p0}")
                        //navigate to OTP screen
                        startActivity(Intent(this@UserLoginActivity, OTPActivity::class.java).putExtra("vid", "$p0"))

                    }

                    override fun onCodeAutoRetrievalTimeOut(p0: String) {
                        super.onCodeAutoRetrievalTimeOut(p0)
                    }

                }) // OnVerificationStateChangedCallbacks
                .build()

            PhoneAuthProvider.verifyPhoneNumber(options)




        }

        binding.btnAdmin.setOnClickListener {
            startActivity(Intent(this, AdminSplashActivity::class.java))
        }
    }
}