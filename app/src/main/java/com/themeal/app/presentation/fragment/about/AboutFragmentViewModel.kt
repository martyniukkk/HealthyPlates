package com.themeal.app.presentation.fragment.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AboutFragmentViewModel : ViewModel() {

    private val _viewPagerPositionIdLive = MutableLiveData<Int>()
    val viewPagerPositionIdLive: LiveData<Int> = _viewPagerPositionIdLive

    fun saveViewPagerPositionId(id: Int) {
        _viewPagerPositionIdLive.value = id
    }
}