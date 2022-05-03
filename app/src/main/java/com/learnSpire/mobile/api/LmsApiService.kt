package com.learnSpire.mobile.api

import com.learnSpire.mobile.models.User
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST

interface LmsApiService {

    @GET("/user/getuser")
    fun getUser(): Call<User>

    @POST("/user/adduser")
    fun addUser(): Call<ResponseBody>

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