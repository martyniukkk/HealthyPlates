package com.themeal.app.presentation.recycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.themeal.app.R

class RecyclerViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val root: View = itemView.findViewById(R.id.root)
    val itemImage: ImageView = itemView.findViewById(R.id.itemImage)
    val itemTitle: TextView = itemView.findViewById(R.id.itemTitle)
}