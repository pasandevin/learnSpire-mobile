package com.learnSpire.mobile.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.learnSpire.mobile.adapters.NotificationsAdapter
import com.learnSpire.mobile.api.LmsApiService
import com.learnSpire.mobile.databinding.ActivityNotificationContentBinding
import com.learnSpire.mobile.models.GetNotificationContentRequest
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

        // create get content request
        val getNotificationContentRequest = GetNotificationContentRequest(courseId, notificationTitle)

        // call the get enrolled courses api
        var getNotificationContentResponse = lmsApiService.getNotificationContent(getNotificationContentRequest)


        getNotificationContentResponse.enqueue(object: Callback<Notification> {
            override fun onResponse(call: Call<Notification>, response: Response<Notification>) {
                val body = response.body()

                body.let {
                    if (it != null) {
                        val notificationContent = it as Notification
                        binding.textNotificationTitle.text = notificationContent.title
                        binding.textNotificationContent.text = notificationContent.content
                        binding.textNotificationDate.text = notificationContent.timeStamp.substring(0, 10)
                    }
                }
            }

            override fun onFailure(call: Call<Notification>, t: Throwable) {
                println("Get Notification Content failed")
            }
        })
    }
}