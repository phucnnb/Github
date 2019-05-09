package com.example.democardslide

interface ViewImp {
    fun prepareData(listData: ArrayList<Item>)
    fun cardStackView(listData: List<Item>)
    fun slidePanel()
    fun reload(listData: List<Item>)
    fun rewind()
}