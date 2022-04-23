package com.pasandevin.android.seng22243.api


import com.learnSpire.mobile.api.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitConfiguration {
    //fixme use real endpoints
    @GET("users" )
    fun getUsers(): Call<List<User>>
//
    @GET("users/{userId}")
    fun getUser(@Path("userId") id:String ): Call<User>
//
//    // RecyclerView //
//    @GET("photos" )
//    fun getPhotos(): Call<List<Photo>>
    // RecyclerView //

    companion object {
        val API_URL =  "https://jsonplaceholder.typicode.com/"
        fun create(): RetrofitConfiguration {
            val retrofit = Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(RetrofitConfiguration::class.java)

        }
    }

}