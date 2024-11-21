package com.themeal.app.domain.usecase.meal_storage

import com.themeal.app.domain.model.Meal
import com.themeal.app.domain.repository.MealStorageRepository

class RemoveMeal(private val mealStorageRepository: MealStorageRepository) {

    fun execute(meal: Meal): Boolean {
        return mealStorageRepository.removeMeal(meal = meal)
    }
}