package com.themeal.app.presentation.fragment_manager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.themeal.app.R

object NavigationHelper {

    private fun isFragmentInBackStack(
        fragmentManager: FragmentManager,
    ): Boolean {
        val backStackEntryCount = fragmentManager.backStackEntryCount
        for (i in 0 until backStackEntryCount) {
            val backStackEntry = fragmentManager.getBackStackEntryAt(i)
            if (backStackEntry.name == "meal") {
                return true
            }
        }
        return false
    }

    fun goToFragment(
        fragment: Fragment,
        fragmentManager: FragmentManager,
        addToBackStack: Boolean = true,
        replaceLayoutId: Int = R.id.frame
    ) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(replaceLayoutId, fragment)
        if (addToBackStack && !isFragmentInBackStack(fragmentManager)) {
            transaction.addToBackStack("meal")
        }
        transaction.commit()
    }
}