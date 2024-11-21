package com.themeal.app.presentation.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.themeal.app.R
import com.themeal.app.common.Constants
import com.themeal.app.domain.model.MealList
import com.themeal.app.presentation.activity.MainViewModel
import com.themeal.app.presentation.fragment.meal.MealFragment

class RecyclerViewAdapter(
    private val context: Context,
    private val mealList: MealList,
    private val mainViewModel: MainViewModel
) : RecyclerView.Adapter<RecyclerViewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        val recyclerItem =
            LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false)
        return RecyclerViewViewHolder(itemView = recyclerItem)
    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        val meal = mealList.list[position]
        if (meal.strMealThumb != null) {
            Picasso.get().load(meal.strMealThumb).placeholder(R.drawable.image_24px)
                .into(holder.itemImage)
        }
        holder.itemTitle.text = meal.strMeal.toString()
        holder.root.setOnClickListener {
            if (meal.idMeal == null) {
                Toast.makeText(context, Constants.MEAL_ERROR_MESSAGE, Toast.LENGTH_SHORT).show()
            } else {
                val mealFragment = MealFragment.newInstance(mealId = meal.idMeal.toString())
                mainViewModel.setFragment(fragment = mealFragment)
            }
        }
    }

    override fun getItemCount(): Int {
        return mealList.list.size
    }
}