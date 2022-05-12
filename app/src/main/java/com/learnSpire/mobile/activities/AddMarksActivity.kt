package com.learnSpire.mobile.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.learnSpire.mobile.adapters.EnrolledCoursesAdapter
import com.learnSpire.mobile.api.LmsApiService
import com.learnSpire.mobile.databinding.ActivityAddMarksBinding
import com.learnSpire.mobile.models.GetCourseMarksResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddMarksActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddMarksBinding

    private val lmsApiService = LmsApiService.create()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddMarksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //get courseId and courseName from previous activity
        val courseId = EnrolledCoursesAdapter.courseId
        val courseName = EnrolledCoursesAdapter.courseName

        var courseMarksList = ArrayList<GetCourseMarksResponse>()

        // call the add course api
        var getCourseMarksResponse = lmsApiService.getAllMarksForACourse(courseId)

        getCourseMarksResponse.enqueue(object: Callback<List<GetCourseMarksResponse>> {
            override fun onResponse(call: Call<List<GetCourseMarksResponse>>, responseGet: Response<List<GetCourseMarksResponse>>) {
                val body = responseGet.body()

                body.let {
                    if (it != null) {
                        courseMarksList = it as ArrayList<GetCourseMarksResponse>
                        val marksList2 = courseMarksList.toList()

                        println(marksList2)


                    }
                }
            }

            override fun onFailure(call: Call<List<GetCourseMarksResponse>>, t: Throwable) {
                println("Get all marks for a course failed")
            }
        })




    }
}