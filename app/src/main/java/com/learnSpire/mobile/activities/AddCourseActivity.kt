package com.learnSpire.mobile.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.learnSpire.mobile.R
import com.learnSpire.mobile.api.LmsApiService
import com.learnSpire.mobile.databinding.ActivityAddCourseBinding
import com.learnSpire.mobile.models.Course
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddCourseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCourseBinding

    private val lmsApiService = LmsApiService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_course)

        //set activity title as add course
        title = "Add Course"

        binding = ActivityAddCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSubmit.setOnClickListener {
            var courseId = binding.editTextCourseId.text.toString()
            var courseName = binding.editTextCourseName.text.toString()

            // check if the course id and name is empty or not
            if (courseId.isEmpty() || courseName.isEmpty()) {

                binding.errorTextView.text = "Please fill all the fields"

            } else {

                // create a new course object
                var course = Course(courseId, courseName)

                // call the add course api
                var addCourseResponse = lmsApiService.addNewCourse(course)

                addCourseResponse.enqueue(object : Callback<ResponseBody> {

                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {

                            // show success message
                            Toast.makeText(
                                this@AddCourseActivity,
                                "Course Added Successfully",
                                Toast.LENGTH_SHORT
                            ).show()

                            // go to the course list fragment
                            Intent(this@AddCourseActivity, LecturerMenuActivity::class.java).also {
                                startActivity(it)
                            }

                        } else {
                            // show error message
                            println("Failed to add course")
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        println("Failed to add course")
                    }
                })
            }
        }
    }
}
