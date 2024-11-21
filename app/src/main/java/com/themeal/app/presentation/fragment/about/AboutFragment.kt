package com.themeal.app.presentation.fragment.about

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.orhanobut.hawk.Hawk
import com.themeal.app.common.Constants
import com.themeal.app.databinding.FragmentAboutBinding
import com.themeal.app.presentation.activity.MainViewModel
import com.themeal.app.presentation.fragment.about.viewpager2.ViewPager2Adapter
import com.themeal.app.presentation.fragment.main.MainFragment
import com.themeal.app.presentation.fragment_manager.NavigationHelper
import com.themeal.app.presentation.viewmodel.AboutViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding
    private val aboutViewModel by activityViewModel<AboutViewModel>()
    private val mainViewModel by activityViewModel<MainViewModel>()
    private val aboutFragmentViewModel by activityViewModel<AboutFragmentViewModel>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        mainViewModel.setFragment(this)
        aboutViewModel.receiveAboutItemList()
        aboutViewModel.aboutItemListLive.observe(viewLifecycleOwner) { aboutItemList ->
            binding.viewPager2.adapter = ViewPager2Adapter(
                context = requireActivity().applicationContext,
                aboutItemList = aboutItemList
            )
            binding.dotsIndicator.attachTo(binding.viewPager2)
            binding.continueBtn.setOnClickListener {
                if (binding.viewPager2.currentItem + 1 > aboutItemList.list.lastIndex) {
                    NavigationHelper.goToFragment(
                        fragment = MainFragment(),
                        fragmentManager = parentFragmentManager,
                        addToBackStack = false
                    )
                    Hawk.put(Constants.SHOULD_OPEN_MAIN_FRAGMENT_FIRST, true)
                } else {
                    binding.viewPager2.currentItem++
                }
            }
        }
        aboutFragmentViewModel.viewPagerPositionIdLive.observe(viewLifecycleOwner) { id ->
            if (binding.viewPager2.currentItem != id) {
                binding.viewPager2.setCurrentItem(id, false)
            }
        }
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                aboutFragmentViewModel.saveViewPagerPositionId(id = position)
            }
        })
        return binding.root
    }
}