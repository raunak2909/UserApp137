package com.example.firebaseproject137

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebaseproject137.admin.AdminSplashActivity
import com.example.firebaseproject137.databinding.ActivityUserLoginBinding

class UserLoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSingUp.setOnClickListener {
            startActivity(Intent(this,UserSingUpActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.btnAdmin.setOnClickListener {
            startActivity(Intent(this, AdminSplashActivity::class.java))
        }
    }
}