package com.themeal.app.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.themeal.app.R
import com.themeal.app.presentation.fragment.meal.MealFragment
import com.themeal.app.presentation.fragment_manager.NavigationHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.currentFragment.observe(this) { fragment ->
            Log.d("mLog", (fragment is MealFragment).toString())
            NavigationHelper.goToFragment(
                fragment = fragment,
                fragmentManager = supportFragmentManager,
                addToBackStack = fragment is MealFragment
            )
        }
    }
}