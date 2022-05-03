package com.learnSpire.mobile.api

import android.content.Context
import com.learnSpire.mobile.MyApplication
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class TokenInterceptor : Interceptor {

    // get application context
    var applicationContext: Context? = MyApplication.contextOfApplication

    // get shared preferences
    var prefs = applicationContext?.getSharedPreferences("SharedPrefFile", Context.MODE_PRIVATE)

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        //rewrite the request to add bearer token
        val newRequest: Request = chain.request().newBuilder()
            .header("Authorization", "Bearer ${prefs?.getString("token", "")}")
            .build()

        return chain.proceed(newRequest)
    }
}