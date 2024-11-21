package com.themeal.app.presentation.di

import com.themeal.app.domain.usecase.about_storage.GetAboutItemList
import com.themeal.app.domain.usecase.meal_storage.GetSavedMealList
import com.themeal.app.domain.usecase.meal_storage.RemoveMeal
import com.themeal.app.domain.usecase.meal_storage.SaveMeal
import com.themeal.app.domain.usecase.remote.GetMealById
import com.themeal.app.domain.usecase.remote.GetMealList
import com.themeal.app.domain.usecase.remote.SearchMealList
import org.koin.dsl.module

val domainModule = module {

    factory<GetAboutItemList> {
        GetAboutItemList(aboutStorageRepository = get())
    }

    factory<GetMealById> {
        GetMealById(apiRepository = get())
    }
    factory<GetMealList> {
        GetMealList(apiRepository = get())
    }
    factory<SearchMealList> {
        SearchMealList(apiRepository = get())
    }

    factory<GetSavedMealList> {
        GetSavedMealList(mealStorageRepository = get())
    }
    factory<RemoveMeal> {
        RemoveMeal(mealStorageRepository = get())
    }
    factory<SaveMeal> {
        SaveMeal(mealStorageRepository = get())
    }
}