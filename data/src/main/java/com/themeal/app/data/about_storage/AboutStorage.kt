package com.themeal.app.data.about_storage

import com.themeal.app.data.about_storage.dto.AboutItemListDto

interface AboutStorage {

    fun getAboutItemList(): AboutItemListDto
}