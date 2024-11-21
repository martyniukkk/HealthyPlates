package com.themeal.app.presentation.activity

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.orhanobut.hawk.Hawk
import com.themeal.app.common.Constants
import com.themeal.app.presentation.fragment.about.AboutFragment
import com.themeal.app.presentation.fragment.main.MainFragment

class MainViewModel : ViewModel() {

    private val _currentFragment = MutableLiveData<Fragment>().also {
        val shouldOpenMainFragmentFirst = Hawk.get(Constants.SHOULD_OPEN_MAIN_FRAGMENT_FIRST, false)
        it.value = if (shouldOpenMainFragmentFirst) MainFragment() else AboutFragment()
    }
    val currentFragment: LiveData<Fragment> = _currentFragment

    fun setFragment(fragment: Fragment) {
        _currentFragment.value = fragment
    }
}