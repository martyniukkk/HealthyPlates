package com.themeal.app.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.themeal.app.domain.model.Meal
import com.themeal.app.domain.model.MealList
import com.themeal.app.domain.usecase.meal_storage.GetSavedMealList
import com.themeal.app.domain.usecase.meal_storage.RemoveMeal
import com.themeal.app.domain.usecase.meal_storage.SaveMeal

class MealViewModel(
    private val getSavedMealList: GetSavedMealList,
    private val removeMeal: RemoveMeal,
    private val saveMeal: SaveMeal
) : ViewModel() {

    private val _savedMealList = MutableLiveData<MealList>()
    val savedMealList: LiveData<MealList> = _savedMealList

    fun getSavedMealList() {
        _savedMealList.value = getSavedMealList.execute()
    }

    fun removeMeal(meal: Meal) {
        removeMeal.execute(meal)
    }

    fun saveMeal(meal: Meal) {
        saveMeal.execute(meal)
    }
}