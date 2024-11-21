package com.themeal.app.domain.usecase.meal_storage

import com.themeal.app.domain.model.MealList
import com.themeal.app.domain.repository.MealStorageRepository

class GetSavedMealList(private val mealStorageRepository: MealStorageRepository) {

    fun execute(): MealList {
        return mealStorageRepository.getSavedMealList()
    }
}