package com.learnSpire.mobile.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.learnSpire.mobile.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Initialized Shared Preferences
        var sharedPreferences = getSharedPreferences("SharedPrefFile", Context.MODE_PRIVATE)

        // get token value from shared preferences
        var token = sharedPreferences.getString("token", "")

        // get role value from shared preferences
        var role = sharedPreferences.getString("role", "")

        // Initialize the image view
        var imageLogo = findViewById<ImageView>(R.id.image_logo)

        // Start the animation
        imageLogo.alpha = 0f

        imageLogo.animate().alpha(1f).setDuration(1500).withEndAction {

            // check if token is empty or not and navigate to appropriate activity
            if (token == "") {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {

                // check if role is lecturer or student and navigate to appropriate activity
                if (role == "lecturer") {
                    val intent = Intent(this, LecturerMenuActivity::class.java)
                    startActivity(intent)
                } else if (role == "student") {
                    val intent = Intent(this, MenuActivity::class.java)
                    startActivity(intent)
                }

            }
        }
    }
}