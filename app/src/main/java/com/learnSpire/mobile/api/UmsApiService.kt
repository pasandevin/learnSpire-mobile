package com.learnSpire.mobile.api

import com.learnSpire.mobile.models.SigninRequest
import com.learnSpire.mobile.models.SigninResponse
import com.learnSpire.mobile.models.SignupRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface UmsApiService {

    @POST("/signin")
    fun signIn(@Body signInRequest: SigninRequest): Call<SigninResponse>

    @POST("/signup")
    fun signUp(@Body signUpRequest: SignupRequest): Call<ResponseBody>

    companion object {
        val API_URL = "https://ums.team8backend.tech"
        fun create(): UmsApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(UmsApiService::class.java)
        }
    }
}