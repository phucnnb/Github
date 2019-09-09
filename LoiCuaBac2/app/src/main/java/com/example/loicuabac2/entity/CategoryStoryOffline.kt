package com.example.loicuabac2.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "StoryOffline")
class CategoryStoryOffline {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id" )
    var id : Int = 0

    @ColumnInfo(name = "name_story" )
    var nameStory : String = ""

    @ColumnInfo(name = "body_story" )
    var bodyStory : String = ""

    @ColumnInfo(name = "code" )
    var code : String = ""
}