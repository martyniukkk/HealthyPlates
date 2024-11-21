package com.themeal.app.presentation.fragment.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.themeal.app.databinding.FragmentSearchBinding
import com.themeal.app.domain.model.SearchParam
import com.themeal.app.presentation.activity.MainViewModel
import com.themeal.app.presentation.recycler.RecyclerViewAdapter
import com.themeal.app.presentation.viewmodel.RemoteViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val mainViewModel by activityViewModel<MainViewModel>()
    private val searchFragmentViewModel by activityViewModel<SearchFragmentViewModel>()
    private val remoteViewModel by activityViewModel<RemoteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val editTextValue = binding.editText.text.toString()
                binding.emptyText.isVisible = editTextValue.isEmpty()
                binding.recycler.isVisible = false
                if (editTextValue.isNotEmpty()) {
                    remoteViewModel.searchMealList(SearchParam(query = editTextValue))
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
        searchFragmentViewModel.editTextValue.observe(viewLifecycleOwner) { value ->
            binding.editText.setText(value)
        }
        remoteViewModel.searchMealListLive.observe(viewLifecycleOwner) { mealList ->
            binding.emptyText.isVisible = mealList.list.isEmpty()
            binding.recycler.isVisible = mealList.list.isNotEmpty()
            binding.recycler.adapter = RecyclerViewAdapter(
                context = requireContext(),
                mealList = mealList!!,
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
                searchFragmentViewModel.saveRecyclerScrollPosition(position, offset)
            }
        })
        searchFragmentViewModel.recyclerScrollPositionLive.observe(viewLifecycleOwner) { (position, offset) ->
            (binding.recycler.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
                position,
                offset
            )
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        remoteViewModel.clearSearchMealList()
        searchFragmentViewModel.saveEditTextValue(binding.editText.text.toString())
    }
}