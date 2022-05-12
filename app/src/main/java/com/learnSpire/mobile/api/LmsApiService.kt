package com.learnSpire.mobile.api

import com.learnSpire.mobile.models.*
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LmsApiService {

    @POST("/mark/getenrolledstudents")
    fun getAllMarksForACourse(@Body courseId: String): Call<List<CourseMarksResponse>>

    @POST("/content/addcontent")
    fun addContent(@Body content: AddContentRequest): Call<ResponseBody>

    @POST("/announcement/addannouncement")
    fun addAnnouncement(@Body announcement: Announcement): Call<ResponseBody>

    @POST("/course/addnewcourse")
    fun addNewCourse(@Body course: Course): Call<ResponseBody>

    @POST("/announcement/getnotification")
    fun getNotificationContent(@Body getNotificationContentRequest: GetNotificationContentRequest): Call<Notification>

    @GET("/announcement/getnotifications")
    fun getAllNotifications(): Call<List<GetNotificationsResponse>>

    @GET("mark/getallmarks")
    fun getAllMarks(): Call<List<MarksResponse>>

    @GET("/user/getuser")
    fun getUser(): Call<User>

    @POST("/user/adduser")
    fun addUser(): Call<ResponseBody>

    @GET("/course/enrolledcourses")
    fun getEnrolledCourses(): Call<List<Course>>

    @GET("/course/availablecourses")
    fun getAvailableCourses(): Call<List<Course>>

    @POST("/course/adduser")
    fun enrollCourse(@Body enrollCourseRequest: EnrollCourseRequest): Call<ResponseBody>

    @POST("/content/getcontent")
    fun getContent(@Body getContentRequest: GetContentRequest): Call<List<Content>>

    companion object {

        val API_URL = "https://lms.team8backend.tech"

        fun create(): LmsApiService {

            val client = OkHttpClient.Builder()
                .addInterceptor(TokenInterceptor())
                .build();

            val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(LmsApiService::class.java)
        }
    }
}