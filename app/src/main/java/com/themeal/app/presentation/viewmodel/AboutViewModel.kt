package com.themeal.app.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.themeal.app.domain.model.AboutItemList
import com.themeal.app.domain.usecase.about_storage.GetAboutItemList

class AboutViewModel(
    private val getAboutItemList: GetAboutItemList
) : ViewModel() {

    private val _aboutItemListLive = MutableLiveData<AboutItemList>()
    val aboutItemListLive: LiveData<AboutItemList> = _aboutItemListLive

    fun receiveAboutItemList() {
        _aboutItemListLive.value = getAboutItemList.execute()
    }
}