package com.themeal.app.presentation.fragment.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainFragmentViewModel : ViewModel() {

    private val _selectedBottomNavItemIdLive = MutableLiveData<Int>()
    val selectedBottomNavItemIdLive: LiveData<Int> = _selectedBottomNavItemIdLive

    fun setSelectedBottomNavItemId(itemId: Int) {
        _selectedBottomNavItemIdLive.value = itemId
    }
}