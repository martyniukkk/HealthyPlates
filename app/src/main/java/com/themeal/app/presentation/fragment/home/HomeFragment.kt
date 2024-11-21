package com.themeal.app.presentation.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.themeal.app.databinding.FragmentHomeBinding
import com.themeal.app.presentation.activity.MainViewModel
import com.themeal.app.presentation.recycler.RecyclerViewAdapter
import com.themeal.app.presentation.viewmodel.RemoteViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val mainViewModel by activityViewModel<MainViewModel>()
    private val remoteViewModel by activityViewModel<RemoteViewModel>()
    private val homeFragmentViewModel by activityViewModel<HomeFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        remoteViewModel.receiveMealList()
        binding.progressBar.isVisible = true
        Log.d("mLog", "CREATE VIEW")
        remoteViewModel.mealListLive.observe(viewLifecycleOwner) { mealList ->
            binding.progressBar.isVisible = false
            binding.recycler.adapter = RecyclerViewAdapter(
                context = requireContext(),
                mealList = mealList,
                mainViewModel = mainViewModel
            )
        }
        binding.recycler.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val position = layoutManager.findFirstVisibleItemPosition()
                val view = layoutManager.findViewByPosition(position)
                val offset = view?.top ?: 0
                homeFragmentViewModel.saveRecyclerScrollPosition(position, offset)
            }
        })
        homeFragmentViewModel.recyclerScrollPositionLive.observe(viewLifecycleOwner) { (position, offset) ->
            (binding.recycler.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
                position,
                offset
            )
        }
        return binding.root
    }
}