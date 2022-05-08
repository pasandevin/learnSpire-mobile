package com.learnSpire.mobile.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.learnSpire.mobile.adapters.CourseContentAdapter
import com.learnSpire.mobile.adapters.EnrolledCoursesAdapter
import com.learnSpire.mobile.api.LmsApiService
import com.learnSpire.mobile.databinding.ActivityCourseContentBinding
import com.learnSpire.mobile.models.Content
import com.learnSpire.mobile.models.GetContentRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CourseContentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCourseContentBinding

    private val lmsApiService = LmsApiService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCourseContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var courseContentList = ArrayList<Content>()

        // get course id
        val courseId = EnrolledCoursesAdapter.courseId

        // create get content request
        val getContentRequest = GetContentRequest(courseId)

        // call the get enrolled courses api
        var getContentResponse = lmsApiService.getContent(getContentRequest)

        getContentResponse.enqueue(object: Callback<List<Content>> {
            override fun onResponse(call: Call<List<Content>>, response: Response<List<Content>>) {
                val body = response.body()

                body.let {
                    if (it != null) {
                        courseContentList = it as ArrayList<Content>

                        // set recycler view
                        val recyclerView = binding.recyclerviewCourseContent
                        recyclerView.layoutManager = LinearLayoutManager(this@CourseContentActivity)

                        // set adapter
                        val adapter = CourseContentAdapter(courseContentList)
                        recyclerView.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<List<Content>>, t: Throwable) {
                println("Get Content failed")
            }
        })

    }
}