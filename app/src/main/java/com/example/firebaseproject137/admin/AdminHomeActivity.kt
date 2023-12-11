package com.example.firebaseproject137.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebaseproject137.R
import com.example.firebaseproject137.UserModal
import com.example.firebaseproject137.databinding.ActivityAdminHomeBinding

class AdminHomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityAdminHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var arrData = ArrayList<UserModal>().apply {
            add(UserModal(R.drawable.person, "rohan", "9709123458"))
            add(UserModal(R.drawable.person, "rajit", "9709123458"))
            add(UserModal(R.drawable.person, "suman", "9709123458"))
            add(UserModal(R.drawable.person, "Sachin", "9709123458"))
        }

        binding.recyclerViewUser.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewUser.adapter = RecyclerUserAdpter(this, arrData)

    }
}