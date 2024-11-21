package com.themeal.app.presentation.di

import com.themeal.app.presentation.activity.MainViewModel
import com.themeal.app.presentation.fragment.about.AboutFragmentViewModel
import com.themeal.app.presentation.fragment.bookmarks.BookmarksFragmentViewModel
import com.themeal.app.presentation.fragment.home.HomeFragmentViewModel
import com.themeal.app.presentation.fragment.main.MainFragmentViewModel
import com.themeal.app.presentation.fragment.search.SearchFragmentViewModel
import com.themeal.app.presentation.viewmodel.AboutViewModel
import com.themeal.app.presentation.viewmodel.MealViewModel
import com.themeal.app.presentation.viewmodel.RemoteViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<AboutFragmentViewModel> {
        AboutFragmentViewModel()
    }

    viewModel<AboutViewModel> {
        AboutViewModel(getAboutItemList = get())
    }

    viewModel<BookmarksFragmentViewModel> {
        BookmarksFragmentViewModel()
    }

    viewModel<HomeFragmentViewModel> {
        HomeFragmentViewModel()
    }

    viewModel<MainFragmentViewModel> {
        MainFragmentViewModel()
    }

    viewModel<MealViewModel> {
        MealViewModel(
            getSavedMealList = get(),
            removeMeal = get(),
            saveMeal = get()
        )
    }

    viewModel<MainViewModel> {
        MainViewModel()
    }

    viewModel<RemoteViewModel> {
        RemoteViewModel(
            getMealById = get(),
            getMealList = get(),
            searchMealList = get()
        )
    }

    viewModel<SearchFragmentViewModel> {
        SearchFragmentViewModel()
    }
}