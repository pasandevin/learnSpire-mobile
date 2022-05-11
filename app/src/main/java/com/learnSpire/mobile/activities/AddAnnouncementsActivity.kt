package com.learnSpire.mobile.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.learnSpire.mobile.R
import com.learnSpire.mobile.adapters.EnrolledCoursesAdapter

class AddAnnouncementsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_announcements)

        val courseId = EnrolledCoursesAdapter.courseId
        val courseName = EnrolledCoursesAdapter.courseName



    }
}