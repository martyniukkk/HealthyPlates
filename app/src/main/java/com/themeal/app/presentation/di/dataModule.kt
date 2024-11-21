package com.themeal.app.presentation.di

import com.themeal.app.data.about_storage.AboutStorage
import com.themeal.app.data.about_storage.AboutStorageImpl
import com.themeal.app.data.meal_storage.MealStorage
import com.themeal.app.data.meal_storage.MealStorageImpl
import com.themeal.app.data.remote.MealApi
import com.themeal.app.data.remote.MealApiImpl
import com.themeal.app.data.repository.AboutStorageRepositoryImpl
import com.themeal.app.data.repository.ApiRepositoryImpl
import com.themeal.app.data.repository.MealStorageRepositoryImpl
import com.themeal.app.domain.repository.AboutStorageRepository
import com.themeal.app.domain.repository.ApiRepository
import com.themeal.app.domain.repository.MealStorageRepository
import org.koin.dsl.module

val dataModule = module {

    single<AboutStorage> {
        AboutStorageImpl()
    }
    single<MealApi> {
        MealApiImpl()
    }
    single<MealStorage> {
        MealStorageImpl(context = get())
    }

    single<AboutStorageRepository> {
        AboutStorageRepositoryImpl(aboutStorage = get())
    }
    single<ApiRepository> {
        ApiRepositoryImpl(mealApi = get())
    }
    single<MealStorageRepository> {
        MealStorageRepositoryImpl(mealStorage = get())
    }
}