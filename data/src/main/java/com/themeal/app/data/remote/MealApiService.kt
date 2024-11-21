package com.themeal.app.data.remote

import com.themeal.app.data.remote.dto.MealListDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {

    @GET("api/json/v1/1/lookup.php")
    fun getById(@Query("i") id: String): Call<MealListDto>

    @GET("api/json/v1/1/search.php")
    fun searchList(@Query("s") query: String): Call<MealListDto>

    @GET("api/json/v1/1/filter.php")
    fun getList(@Query("c") category: String = "Vegetarian"): Call<MealListDto>
}