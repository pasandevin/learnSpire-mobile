package com.learnSpire.mobile.activities

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
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


        binding = ActivityAddCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSubmit.setOnClickListener {
            var courseId = binding.editTextCourseId.text.toString()
            var courseName = binding.editTextCourseName.text.toString()

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
                        Toast.makeText(this@AddCourseActivity, "Course Added Successfully", Toast.LENGTH_SHORT).show()

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
