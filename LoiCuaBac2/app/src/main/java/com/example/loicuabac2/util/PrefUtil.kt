package com.example.loicuabac2.util

import android.content.Context

class PrefUtil {
    object data {
        fun putString(context : Context, category : String, key : String, value : String) {
            val settings = context.getSharedPreferences(category, Context.MODE_PRIVATE)
            val editor = settings.edit()
            editor.putString(key, value)
            editor.apply()
        }

        fun getString(context : Context, category : String, key : String, defaultValue : String) : String {
            val settings = context.getSharedPreferences(category, Context.MODE_PRIVATE)
            return settings.getString(key, defaultValue)
        }

        fun putFloat(context : Context, category : String, key : String, value : Float) {
            val settings = context.getSharedPreferences(category, Context.MODE_PRIVATE)
            val editor = settings.edit()
            editor.putFloat(key, value)
            editor.apply()
        }

        fun getFloat(context : Context, category : String, key : String, defaultValue : Float) : Float {
            val settings = context.getSharedPreferences(category, Context.MODE_PRIVATE)
            return settings.getFloat(key, defaultValue)
        }
    }

}