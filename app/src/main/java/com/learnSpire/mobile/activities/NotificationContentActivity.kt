package com.learnSpire.mobile.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.learnSpire.mobile.adapters.CourseContentAdapter
import com.learnSpire.mobile.adapters.EnrolledCoursesAdapter
import com.learnSpire.mobile.adapters.NotificationsAdapter
import com.learnSpire.mobile.api.LmsApiService
import com.learnSpire.mobile.databinding.ActivityNotificationContentBinding
import com.learnSpire.mobile.models.Content
import com.learnSpire.mobile.models.GetContentRequest
import com.learnSpire.mobile.models.Notification
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationContentActivity  : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationContentBinding

    private val lmsApiService = LmsApiService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNotificationContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get course id and name
        val courseId = NotificationsAdapter.courseId
        val courseName = NotificationsAdapter.courseName
        val notificationTitle = NotificationsAdapter.title

        // set activity title as course name
        setTitle(courseName)

        var notificationContentList = ArrayList<Notification>()

        // create get content request
        val getNotificationContentRequest = GetNotificationContentRequest(courseId, notificationTitle)

        // call the get enrolled courses api
        var getContentResponse = lmsApiService.getNotificationContent(getNotificationContentRequest)

        getContentResponse.enqueue(object: Callback<List<Notification>> {
            override fun onResponse(call: Call<List<Notification>>, response: Response<List<Notification>>) {
                val body = response.body()

                body.let {
                    if (it != null) {
                        notificationContentList = it as ArrayList<Notification>

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
                println("Get Notification Content failed")
            }
        })
    }
}