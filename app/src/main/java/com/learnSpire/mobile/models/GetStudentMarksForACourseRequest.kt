package com.learnSpire.mobile.models

data class GetStudentMarksForACourseRequest (
    val studentEmail : String,
    val courseId : String
    )