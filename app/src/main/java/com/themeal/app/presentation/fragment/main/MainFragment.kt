package com.themeal.app.presentation.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.themeal.app.R
import com.themeal.app.databinding.FragmentMainBinding
import com.themeal.app.presentation.activity.MainViewModel
import com.themeal.app.presentation.fragment.bookmarks.BookmarksFragment
import com.themeal.app.presentation.fragment.home.HomeFragment
import com.themeal.app.presentation.fragment.search.SearchFragment
import com.themeal.app.presentation.fragment_manager.NavigationHelper
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val mainViewModel by activityViewModel<MainViewModel>()
    private val mainFragmentViewModel by activityViewModel<MainFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        mainViewModel.setFragment(this)
        binding.bottomNavView.setOnItemSelectedListener { menuItem ->
            mainFragmentViewModel.setSelectedBottomNavItemId(menuItem.itemId)
            true
        }
        openFragment(HomeFragment())
        mainFragmentViewModel.selectedBottomNavItemIdLive.observe(viewLifecycleOwner) { itemId ->
            val fragment = when (itemId) {
                R.id.homeFragment -> HomeFragment()
                R.id.searchFragment -> SearchFragment()
                else -> BookmarksFragment()
            }
            openFragment(fragment = fragment)
        }
        return binding.root
    }

    private fun openFragment(fragment: Fragment) {
        NavigationHelper.goToFragment(
            fragment = fragment,
            fragmentManager = parentFragmentManager,
            addToBackStack = false,
            replaceLayoutId = R.id.bottomNavFrame
        )
    }
}