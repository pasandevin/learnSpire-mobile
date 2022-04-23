package com.learnSpire.mobile.menu.ui.marks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MarksViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is marks Fragment"
    }
    val text: LiveData<String> = _text
}