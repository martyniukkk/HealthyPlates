package com.themeal.app.presentation.fragment.about.viewpager2

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.themeal.app.R

class ViewPager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val itemImage: ImageView = itemView.findViewById(R.id.itemImage)
    val itemTitle: TextView = itemView.findViewById(R.id.itemTitle)
    val itemContent: TextView = itemView.findViewById(R.id.itemContent)
}