package com.learnSpire.mobile.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.learnSpire.mobile.R
import com.learnSpire.mobile.databinding.ActivityAddMarksBinding
import com.learnSpire.mobile.databinding.ActivityLecturerCourseContentBinding
import com.learnSpire.mobile.databinding.ActivityLecturerMenuBinding

class AddMarksActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddMarksBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddMarksBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}