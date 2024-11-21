package com.themeal.app.data.remote

import com.themeal.app.common.Constants
import com.themeal.app.data.remote.dto.MealDto
import com.themeal.app.data.remote.dto.MealIdDto
import com.themeal.app.data.remote.dto.MealListDto
import com.themeal.app.data.remote.dto.SearchParamDto
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory

class MealApiImpl : MealApi {

    private val api: MealApiService by lazy {
        Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealApiService::class.java)
    }

    override suspend fun getById(mealIdDto: MealIdDto): MealDto {
        val nullMealDto = MealDto(null, null, null, null, null, null, null)
        return api.getById(id = mealIdDto.id).await().meals?.get(0) ?: nullMealDto
    }

    override suspend fun getList(): MealListDto {
        return api.getList().await()
    }

    override suspend fun searchList(searchParamDto: SearchParamDto): MealListDto {
        return api.searchList(query = searchParamDto.query).await()
    }
}