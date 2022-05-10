package com.learnSpire.mobile.activities

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.learnSpire.mobile.R
import com.learnSpire.mobile.databinding.ActivityLecturerMenuBinding

class LecturerMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLecturerMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLecturerMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_lecturer_menu)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_courses,
                R.id.navigation_add,
                R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}