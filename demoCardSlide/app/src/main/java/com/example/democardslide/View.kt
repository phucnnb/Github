package com.example.democardslide

interface ViewImp {
    fun prepareData(listData: ArrayList<Item>)
    fun cardStackView(listData: List<Item>)
    fun slidePanel(listData: List<Item>, check: Int)
    fun reload(listData: List<Item>)
    fun rewind()
    fun prepareList(your_array_list: List<String>)
}