package com.themeal.app.data.meal_storage

import com.themeal.app.data.meal_storage.dto.MealDto
import com.themeal.app.data.meal_storage.dto.MealListDto

interface MealStorage {

    fun getList(): MealListDto

    fun remove(mealDto: MealDto): Boolean

    fun save(mealDto: MealDto): Boolean
}