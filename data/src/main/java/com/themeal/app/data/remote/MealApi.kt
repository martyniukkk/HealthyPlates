package com.themeal.app.data.remote

import com.themeal.app.data.remote.dto.MealDto
import com.themeal.app.data.remote.dto.MealIdDto
import com.themeal.app.data.remote.dto.MealListDto
import com.themeal.app.data.remote.dto.SearchParamDto

interface MealApi {

    suspend fun getById(mealIdDto: MealIdDto): MealDto

    suspend fun getList(): MealListDto

    suspend fun searchList(searchParamDto: SearchParamDto): MealListDto
}