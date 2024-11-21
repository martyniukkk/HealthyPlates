package com.themeal.app.domain.repository

import com.themeal.app.domain.model.AboutItemList

interface AboutStorageRepository {

    fun getAboutItemList(): AboutItemList
}