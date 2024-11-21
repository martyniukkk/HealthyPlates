package com.themeal.app.presentation.fragment.meal

import android.annotation.SuppressLint
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.themeal.app.R
import com.themeal.app.common.Constants
import com.themeal.app.databinding.FragmentMealBinding
import com.themeal.app.domain.model.Meal
import com.themeal.app.domain.model.MealId
import com.themeal.app.domain.model.MealList
import com.themeal.app.presentation.viewmodel.MealViewModel
import com.themeal.app.presentation.viewmodel.RemoteViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class MealFragment : Fragment() {

    private lateinit var binding: FragmentMealBinding
    private val mealViewModel by activityViewModel<MealViewModel>()
    private val remoteViewModel by activityViewModel<RemoteViewModel>()

    private var mealId: String? = null
    private var meal: Meal? = null
    private var savedMealList: MealList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mealId = it.getString(Constants.ARG_MEAL_ID)
        }
    }

    companion object {

        fun newInstance(mealId: String) = MealFragment().apply {
            arguments = Bundle().apply {
                putString(Constants.ARG_MEAL_ID, mealId)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMealBinding.inflate(inflater, container, false)
        binding = FragmentMealBinding.inflate(layoutInflater)
        binding.back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        mealViewModel.getSavedMealList()
        mealViewModel.savedMealList.observe(viewLifecycleOwner) { mealList ->
            savedMealList = mealList
            binding.save.isActivated = savedMealList!!.list.any { it.idMeal == mealId }
        }
        binding.save.setOnClickListener {
            if (savedMealList != null && meal != null) {
                if (savedMealList!!.list.any { it.idMeal == mealId }) {
                    mealViewModel.removeMeal(meal!!)
                } else {
                    mealViewModel.saveMeal(meal!!)
                }
                mealViewModel.getSavedMealList()
            }
        }
        remoteViewModel.receiveMealById(mealId = MealId(id = mealId.toString()))
        remoteViewModel.mealLive.observe(viewLifecycleOwner) { meal ->
            if (meal != null) {
                this.meal = meal
                binding.mealLayout.isVisible = true
                binding.progressBar.isVisible = false
                if (meal.strMealThumb != null) {
                    Picasso.get().load(meal.strMealThumb).placeholder(R.drawable.image_24px)
                        .into(binding.itemImage)
                }
                binding.itemTitle.text = meal.strMeal.toString()
                binding.itemCategory.text = "Category: ${meal.strCategory}"
                binding.itemArea.text = "Area: ${meal.strArea}"
                binding.itemInstructions.text = meal.strInstructions.toString()
                binding.watch.isVisible = meal.strYoutube != null
                if (meal.strYoutube != null) {
                    binding.watch.setOnClickListener {
                        try {
                            startActivity(Intent(ACTION_VIEW, Uri.parse(meal.strYoutube)))
                        } catch (ex: Exception) {
                            Toast.makeText(
                                requireContext(),
                                "Can't open the video",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            } else {
                binding.mealLayout.isVisible = false
                binding.progressBar.isVisible = true
            }
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        remoteViewModel.clearMeal()
    }
}