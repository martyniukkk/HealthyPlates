package com.themeal.app.data.about_storage

import com.themeal.app.data.R
import com.themeal.app.data.about_storage.dto.AboutItemDto
import com.themeal.app.data.about_storage.dto.AboutItemListDto

class AboutStorageImpl : AboutStorage {

    private val aboutItemList = listOf(
        AboutItemDto(
            title = "Discover Nutritious Recipes",
            content = "Find meals that nourish your body and fit your goals!",
            imageResource = R.drawable.e6
        ),
        AboutItemDto(
            title = "Search for Your Favorite Meals",
            content = "Easily find the perfect meal for your needs!",
            imageResource = R.drawable.e3
        ),
        AboutItemDto(
            title = "Save Meals for Later",
            content = "Your personalized collection of healthy meals, all in one place!",
            imageResource = R.drawable.e8
        ),
        AboutItemDto(
            title = "Healthy Eating Made Simple",
            content = "Transform your eating habits with ease!",
            imageResource = R.drawable.e2
        ),
        AboutItemDto(
            title = "Stay Inspired with New Recipes",
            content = "Stay motivated with new and exciting meal options!",
            imageResource = R.drawable.e9
        )
    )

    override fun getAboutItemList(): AboutItemListDto {
        return AboutItemListDto(list = aboutItemList)
    }
}