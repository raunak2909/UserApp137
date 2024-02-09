package com.example.firebaseproject137

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.firebaseproject137.databinding.ActivityMainBinding
import com.example.firebaseproject137.home.HomeFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var fm: FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fm = supportFragmentManager

        setSupportActionBar(binding.root.findViewById(R.id.myToolbar))

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.root.findViewById(R.id.myToolbar),
            R.string.openDrawer,
            R.string.closeDrawer


        )
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        binding.navigationView.setNavigationItemSelectedListener {

            val itemId = it.itemId

            when (itemId) {
                R.id.drawer_home -> {
                    loagFrag(HomeFragment())
                }
                R.id.drawer_explore -> {
                    loagFrag(ExploreFragment())
                }
                R.id.drawer_create -> {
                    loagFrag(CreatFragment())
                }
                R.id.drawer_notifaction -> {
                    loagFrag(NotifactionFragment())
                }
                else -> {
                    loagFrag(ProfileFragment())
                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

    }

    fun loagFrag(frag: Fragment){
        val ft =  fm.beginTransaction()
        ft.add(R.id.container, frag)
        ft.commit()
    }
}