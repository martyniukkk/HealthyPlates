package com.themeal.app.data.repository

import com.themeal.app.data.about_storage.AboutStorage
import com.themeal.app.domain.model.AboutItem
import com.themeal.app.domain.model.AboutItemList
import com.themeal.app.domain.repository.AboutStorageRepository

class AboutStorageRepositoryImpl(private val aboutStorage: AboutStorage) :
    AboutStorageRepository {

    override fun getAboutItemList(): AboutItemList {
        val aboutItemList = mutableListOf<AboutItem>()
        val storageAboutItemList = aboutStorage.getAboutItemList()
        for (storageAboutItem in storageAboutItemList.list) {
            aboutItemList.add(
                AboutItem(
                    title = storageAboutItem.title,
                    content = storageAboutItem.content,
                    imageResource = storageAboutItem.imageResource
                )
            )
        }
        return AboutItemList(list = aboutItemList)
    }
}