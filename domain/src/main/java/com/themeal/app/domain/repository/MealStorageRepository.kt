package com.themeal.app.domain.repository

import com.themeal.app.domain.model.Meal
import com.themeal.app.domain.model.MealList

interface MealStorageRepository {

    fun getSavedMealList(): MealList

    fun removeMeal(meal: Meal): Boolean

    fun saveMeal(meal: Meal): Boolean
}