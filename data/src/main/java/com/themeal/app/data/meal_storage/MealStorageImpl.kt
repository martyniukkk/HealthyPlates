package com.themeal.app.data.meal_storage

import android.content.Context
import com.themeal.app.data.meal_storage.dto.MealDto
import com.themeal.app.data.meal_storage.dto.MealListDto

class MealStorageImpl(context: Context) : MealStorage {

    private val dbHelper = MealDatabaseHelper(context)

    override fun getList(): MealListDto {
        return MealListDto(list = dbHelper.getAllMeals())
    }

    override fun remove(mealDto: MealDto): Boolean {
        return dbHelper.deleteMeal(mealDto = mealDto)
    }

    override fun save(mealDto: MealDto): Boolean {
        return dbHelper.addMeal(mealDto = mealDto)
    }
}