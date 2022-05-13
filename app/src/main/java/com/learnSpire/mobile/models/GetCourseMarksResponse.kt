package com.learnSpire.mobile.models

data class GetCourseMarksResponse (
    val email: String,
    val firstName: String,
    val lastName: String,
    var marks: Number
)