package com.example.loicuabac2.service.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.loicuabac2.entity.CategoryStoryOffline

@Dao
interface CategoryStoryOfflineDao {

    @Query("SELECT * FROM StoryOffline")
    fun loadAllStory() : LiveData<List<CategoryStoryOffline>>

    @Query("SELECT * FROM StoryOffline")
    fun checkData() : List<CategoryStoryOffline>

    @Query("SELECT * FROM StoryOffline WHERE code = :code")
    fun loadStory(code : Int) : LiveData<CategoryStoryOffline>

    @Insert
    fun insertStory(vararg storyOffline: CategoryStoryOffline)
}