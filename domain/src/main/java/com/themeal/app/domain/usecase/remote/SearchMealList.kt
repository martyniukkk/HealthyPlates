package com.themeal.app.domain.usecase.remote

import com.themeal.app.domain.model.MealList
import com.themeal.app.domain.model.SearchParam
import com.themeal.app.domain.repository.ApiRepository

class SearchMealList(private val apiRepository: ApiRepository) {

    suspend fun execute(searchParam: SearchParam): MealList {
        return apiRepository.searchMealList(searchParam = searchParam)
    }
}