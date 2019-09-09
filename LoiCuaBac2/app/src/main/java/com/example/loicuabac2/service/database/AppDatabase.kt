package com.example.loicuabac2.service.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.loicuabac2.Constants
import com.example.loicuabac2.entity.CategoryStoryOffline
import com.example.loicuabac2.service.database.dao.CategoryStoryOfflineDao

@Database(entities = [CategoryStoryOffline::class], version = Constants.DATABASE_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryStoryOfflineDao(): CategoryStoryOfflineDao

    companion object {

        private const val DATABASE_NAME = Constants.DATABASE_NAME
        private var sInstance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (sInstance == null) {
                sInstance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, DATABASE_NAME)
                    .build()
            }
            return sInstance
        }
    }
}