package com.themeal.app.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.themeal.app.domain.model.Meal
import com.themeal.app.domain.model.MealId
import com.themeal.app.domain.model.MealList
import com.themeal.app.domain.model.SearchParam
import com.themeal.app.domain.usecase.remote.GetMealById
import com.themeal.app.domain.usecase.remote.GetMealList
import com.themeal.app.domain.usecase.remote.SearchMealList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RemoteViewModel(
    private val getMealById: GetMealById,
    private val getMealList: GetMealList,
    private val searchMealList: SearchMealList
) : ViewModel() {

    private var _mealLive = MutableLiveData<Meal?>()
    var mealLive: LiveData<Meal?> = _mealLive

    fun receiveMealById(mealId: MealId) = viewModelScope.launch(Dispatchers.IO) {
        val meal = getMealById.execute(mealId = mealId)
        withContext(Dispatchers.Main) {
            _mealLive.value = meal
        }
    }

    private val _mealListLive = MutableLiveData<MealList>()
    val mealListLive: LiveData<MealList> = _mealListLive

    fun receiveMealList() = viewModelScope.launch(Dispatchers.IO) {
        val mealList = getMealList.execute()
        withContext(Dispatchers.Main) {
            _mealListLive.value = mealList
        }
    }

    private val _searchMealListLive = MutableLiveData<MealList>()
    val searchMealListLive: LiveData<MealList> = _searchMealListLive

    fun searchMealList(searchParam: SearchParam) = viewModelScope.launch(Dispatchers.IO) {
        val mealList = searchMealList.execute(searchParam = searchParam)
        withContext(Dispatchers.Main) {
            _searchMealListLive.value = mealList
        }
    }

    fun clearMeal() {
        _mealLive.value = null
    }

    fun clearSearchMealList() {
        _searchMealListLive.value = MealList(list = emptyList())
    }
}