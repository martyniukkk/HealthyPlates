package com.themeal.app.data.repository

import com.themeal.app.data.meal_storage.MealStorage
import com.themeal.app.data.meal_storage.dto.MealDto
import com.themeal.app.domain.model.Meal
import com.themeal.app.domain.model.MealList
import com.themeal.app.domain.repository.MealStorageRepository

class MealStorageRepositoryImpl(private val mealStorage: MealStorage) :
    MealStorageRepository {

    override fun getSavedMealList(): MealList {
        val mutableMealList = mutableListOf<Meal>()
        val storageMealList = mealStorage.getList()
        for (storageMeal in storageMealList.list) {
            val meal = Meal(
                idMeal = storageMeal.idMeal,
                strMeal = storageMeal.strMeal,
                strCategory = storageMeal.strCategory,
                strArea = storageMeal.strArea,
                strInstructions = storageMeal.strInstructions,
                strMealThumb = storageMeal.strMealThumb,
                strYoutube = storageMeal.strYoutube,
            )
            mutableMealList.add(meal)
        }
        return MealList(list = mutableMealList)
    }

    override fun removeMeal(meal: Meal): Boolean {
        val mealDto = MealDto(
            idMeal = meal.idMeal,
            strMeal = meal.strMeal,
            strCategory = meal.strCategory,
            strArea = meal.strArea,
            strInstructions = meal.strInstructions,
            strMealThumb = meal.strMealThumb,
            strYoutube = meal.strYoutube,
        )
        return mealStorage.remove(mealDto = mealDto)
    }

    override fun saveMeal(meal: Meal): Boolean {
        val mealDto = MealDto(
            idMeal = meal.idMeal,
            strMeal = meal.strMeal,
            strCategory = meal.strCategory,
            strArea = meal.strArea,
            strInstructions = meal.strInstructions,
            strMealThumb = meal.strMealThumb,
            strYoutube = meal.strYoutube,
        )
        return mealStorage.save(mealDto = mealDto)
    }
}