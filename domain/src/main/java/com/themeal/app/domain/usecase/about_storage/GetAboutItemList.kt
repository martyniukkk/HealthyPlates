package com.themeal.app.domain.usecase.about_storage

import com.themeal.app.domain.model.AboutItemList
import com.themeal.app.domain.repository.AboutStorageRepository

class GetAboutItemList(private val aboutStorageRepository: AboutStorageRepository) {

    fun execute(): AboutItemList {
        return aboutStorageRepository.getAboutItemList()
    }
}