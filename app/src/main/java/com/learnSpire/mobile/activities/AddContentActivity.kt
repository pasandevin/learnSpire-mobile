package com.learnSpire.mobile.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.learnSpire.mobile.R
import com.learnSpire.mobile.databinding.ActivityAddCourseBinding
import com.learnSpire.mobile.databinding.ActivityLecturerMenuBinding

class AddContentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCourseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSubmit.setOnClickListener {
            var courseId = binding.editTextCourseId.text.toString()
            var courseName = binding.editTextCourseName.text.toString()

        }


    }
}