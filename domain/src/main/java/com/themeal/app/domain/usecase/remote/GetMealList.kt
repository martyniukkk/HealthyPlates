package com.themeal.app.domain.usecase.remote

import com.themeal.app.domain.model.MealList
import com.themeal.app.domain.repository.ApiRepository

class GetMealList(private val apiRepository: ApiRepository) {

    suspend fun execute(): MealList {
        return apiRepository.getMealList()
    }
}