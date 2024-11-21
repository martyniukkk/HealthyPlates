package com.themeal.app.presentation.fragment.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchFragmentViewModel : ViewModel() {

    private val _recyclerScrollPositionLive = MutableLiveData<Pair<Int, Int>>()
    val recyclerScrollPositionLive: LiveData<Pair<Int, Int>> = _recyclerScrollPositionLive

    fun saveRecyclerScrollPosition(position: Int, offset: Int) {
        _recyclerScrollPositionLive.value = position to offset
    }

    private val _editTextValueLive = MutableLiveData<String>()
    val editTextValue: LiveData<String> = _editTextValueLive

    fun saveEditTextValue(value: String) {
        _editTextValueLive.value = value
    }
}