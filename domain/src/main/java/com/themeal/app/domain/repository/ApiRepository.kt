package com.themeal.app.domain.repository

import com.themeal.app.domain.model.Meal
import com.themeal.app.domain.model.MealId
import com.themeal.app.domain.model.MealList
import com.themeal.app.domain.model.SearchParam

interface ApiRepository {

    suspend fun getMealById(mealId: MealId): Meal

    suspend fun getMealList(): MealList

    suspend fun searchMealList(searchParam: SearchParam): MealList
}