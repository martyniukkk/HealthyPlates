package com.themeal.app.domain.usecase.remote

import com.themeal.app.domain.model.Meal
import com.themeal.app.domain.model.MealId
import com.themeal.app.domain.repository.ApiRepository

class GetMealById(private val apiRepository: ApiRepository) {

    suspend fun execute(mealId: MealId): Meal {
        return apiRepository.getMealById(mealId = mealId)
    }
}