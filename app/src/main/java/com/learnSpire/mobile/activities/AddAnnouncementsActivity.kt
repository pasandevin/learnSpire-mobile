package com.learnSpire.mobile.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.learnSpire.mobile.adapters.EnrolledCoursesAdapter
import com.learnSpire.mobile.api.LmsApiService
import com.learnSpire.mobile.databinding.ActivityAddAnnouncementsBinding
import com.learnSpire.mobile.models.Announcement
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddAnnouncementsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddAnnouncementsBinding

    private val lmsApiService = LmsApiService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddAnnouncementsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val courseId = EnrolledCoursesAdapter.courseId
        val courseName = EnrolledCoursesAdapter.courseName

        binding.buttonSubmit.setOnClickListener {
            var announcementTitle = binding.editTextAnnouncementTitle.text.toString()
            var announcementContent = binding.editTextAnnouncementContent.text.toString()

            var announcement = Announcement(courseId, announcementTitle, announcementContent)

            // call the add course api
            var addAnnouncementResponse = lmsApiService.addAnnouncement(announcement)

            addAnnouncementResponse.enqueue(object : Callback<ResponseBody> {

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {

                        // show success message
                        Toast.makeText(
                            this@AddAnnouncementsActivity,
                            "Announcement Added Successfully",
                            Toast.LENGTH_SHORT
                        ).show()

                        // go to the course list fragment
                        Intent(this@AddAnnouncementsActivity, LecturerMenuActivity::class.java).also {
                            startActivity(it)
                        }

                    } else {
                        // show error message
                        println("Failed to add announcement")
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    println("Failed to add announcement")
                }
            })
        }



    }
}