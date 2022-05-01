package com.learnSpire.mobile.models

data class SignupRequest(
    val email: String,
    val firstName: String,
    val lastName: String,
    val role: String,
    val password: String
)
