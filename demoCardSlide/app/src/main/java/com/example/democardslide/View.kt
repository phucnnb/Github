package com.example.democardslide

interface ViewImp {
    fun PrepareData(listData: ArrayList<Item>)
    fun CardStackView(listData: List<Item>)
    fun SlidePanel()
    fun Reload(listData: List<Item>)
    fun Rewind()
}