package com.learnSpire.mobile.models

import android.icu.text.CaseMap

data class GetNotificationsResponse(
    val courseName: String,
    val courseId: String,
    val title: String
)