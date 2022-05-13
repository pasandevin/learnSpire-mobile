package com.learnSpire.mobile.models

data class AddMarksRequest (
    val courseId : String,
    val studentEmail: String,
    val marks: Number
    )