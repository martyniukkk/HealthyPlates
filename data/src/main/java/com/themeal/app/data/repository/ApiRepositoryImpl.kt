package com.themeal.app.data.repository

import com.themeal.app.data.remote.MealApi
import com.themeal.app.data.remote.dto.MealDto
import com.themeal.app.data.remote.dto.MealIdDto
import com.themeal.app.data.remote.dto.MealListDto
import com.themeal.app.data.remote.dto.SearchParamDto
import com.themeal.app.domain.model.Meal
import com.themeal.app.domain.model.MealId
import com.themeal.app.domain.model.MealList
import com.themeal.app.domain.model.SearchParam
import com.themeal.app.domain.repository.ApiRepository

class ApiRepositoryImpl(private val mealApi: MealApi) : ApiRepository {

    private fun convertApiMealToDefault(mealDto: MealDto): Meal {
        val meal = Meal(
            idMeal = mealDto.idMeal,
            strMeal = mealDto.strMeal,
            strCategory = mealDto.strCategory,
            strArea = mealDto.strArea,
            strInstructions = mealDto.strInstructions,
            strMealThumb = mealDto.strMealThumb,
            strYoutube = mealDto.strYoutube,
        )
        return meal
    }

    private fun convertApiMealListToDefault(mealListDto: MealListDto): MealList {
        val mutableMealList = mutableListOf<Meal>()
        if (mealListDto.meals != null) {
            for (apiMeal in mealListDto.meals) {
                val meal = convertApiMealToDefault(mealDto = apiMeal)
                mutableMealList.add(meal)
            }
        }
        return MealList(list = mutableMealList)
    }

    override suspend fun getMealById(mealId: MealId): Meal {
        val mealIdDto = MealIdDto(id = mealId.id)
        val apiMeal = mealApi.getById(mealIdDto = mealIdDto)
        return convertApiMealToDefault(mealDto = apiMeal)
    }

    override suspend fun getMealList(): MealList {
        val apiMealList = mealApi.getList()
        return convertApiMealListToDefault(mealListDto = apiMealList)
    }

    override suspend fun searchMealList(searchParam: SearchParam): MealList {
        val searchParamDto = SearchParamDto(query = searchParam.query)
        val apiMealList = mealApi.searchList(searchParamDto = searchParamDto)
        return convertApiMealListToDefault(mealListDto = apiMealList)
    }
}