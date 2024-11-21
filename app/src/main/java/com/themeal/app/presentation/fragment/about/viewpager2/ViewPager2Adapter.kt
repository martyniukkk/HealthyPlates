package com.themeal.app.presentation.fragment.about.viewpager2

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.themeal.app.R
import com.themeal.app.domain.model.AboutItemList

class ViewPager2Adapter(private val context: Context, private val aboutItemList: AboutItemList) :
    RecyclerView.Adapter<ViewPager2ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPager2ViewHolder {
        val viewPager2Item =
            LayoutInflater.from(context).inflate(R.layout.viewpager2_item, parent, false)
        return ViewPager2ViewHolder(itemView = viewPager2Item)
    }

    override fun onBindViewHolder(holder: ViewPager2ViewHolder, position: Int) {
        val aboutItem = aboutItemList.list[position]
        holder.itemImage.setImageResource(aboutItem.imageResource)
        holder.itemTitle.text = aboutItem.title
        holder.itemContent.text = aboutItem.content
    }

    override fun getItemCount(): Int {
        return aboutItemList.list.size
    }
}