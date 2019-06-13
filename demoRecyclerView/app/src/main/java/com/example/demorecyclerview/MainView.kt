package com.example.demorecyclerview

interface MainView {
    fun prepareData(listData: ArrayList<Android>)
    fun putData(listData: ArrayList<Android>)
    fun insertData(listDataRecyclerView: ArrayList<Android>)
    fun updateData()
}