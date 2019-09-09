package com.example.loicuabac2.view.category

import com.example.loicuabac2.entity.CategoryImage
import com.example.loicuabac2.entity.CategoryStory
import com.example.loicuabac2.entity.CategoryStoryOffline

interface CategoryView {
    fun updateCategoryStory(listCategoryStory: ArrayList<CategoryStory>)
    fun updateCategoryImage(listCategoryImage: ArrayList<CategoryImage>)
    fun updateCategoryStoryOffline(listCategoryOffline: List<CategoryStoryOffline>)
}