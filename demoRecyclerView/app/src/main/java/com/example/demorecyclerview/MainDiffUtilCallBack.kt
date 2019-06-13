package com.example.demorecyclerview

import android.support.v7.util.DiffUtil

class MainDiffUtilCallBack(private var oldList: ArrayList<Android>, private var newList: ArrayList<Android>) :
    DiffUtil.Callback() {


    override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return oldPosition == newPosition
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return oldList[oldPosition] == newList[newPosition]
    }

}