package com.learnSpire.mobile.models

data class UpdateMarksRequest (
    val courseId : String,
    val studentEmail: String
    val marks: Number
    )