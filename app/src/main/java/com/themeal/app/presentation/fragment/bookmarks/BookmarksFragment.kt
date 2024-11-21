package com.themeal.app.presentation.fragment.bookmarks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.themeal.app.databinding.FragmentBookmarksBinding
import com.themeal.app.presentation.activity.MainViewModel
import com.themeal.app.presentation.recycler.RecyclerViewAdapter
import com.themeal.app.presentation.viewmodel.MealViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class BookmarksFragment : Fragment() {

    private lateinit var binding: FragmentBookmarksBinding
    private val mainViewModel by activityViewModel<MainViewModel>()
    private val mealViewModel by activityViewModel<MealViewModel>()
    private val bookmarksFragmentViewModel by activityViewModel<BookmarksFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarksBinding.inflate(inflater, container, false)
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        mealViewModel.getSavedMealList()
        mealViewModel.savedMealList.observe(viewLifecycleOwner) { mealList ->
            binding.emptyText.isVisible = mealList.list.isEmpty()
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
                bookmarksFragmentViewModel.saveRecyclerScrollPosition(position, offset)
            }
        })
        bookmarksFragmentViewModel.recyclerScrollPositionLive.observe(viewLifecycleOwner) { (position, offset) ->
            (binding.recycler.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
                position,
                offset
            )
        }
        return binding.root
    }
}