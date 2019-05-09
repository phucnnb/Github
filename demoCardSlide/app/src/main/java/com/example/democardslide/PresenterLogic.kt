package com.example.democardslide

import android.content.Context

class PresenterLogic : PresenterImp{

    private var viewImp : ViewImp
    private var  context : Context
    private var listData: List<Item> = ArrayList()

    constructor(view: ViewImp, context: Context) {
        this.viewImp = view
        this.context = context
    }



    override fun ThucHienPrepareListData() {
        val data1 : Item = Item(R.drawable.chicken,"Gà Chiên","Nguyên Liệu Chính Là Gà")
        val data2 : Item = Item(R.drawable.meat,"Thịt Chiên","Nguyên Liệu Chính Là Thịt Heo")
        val data3 : Item = Item(R.drawable.vegetable,"Rau Củ Trộn","Nguyên Liệu Chính Là Rau Củ")
        (listData as ArrayList<Item>).add(data1)
        (listData as ArrayList<Item>).add(data2)
        (listData as ArrayList<Item>).add(data3)
        viewImp.PrepareData(listData as ArrayList<Item>)

    }

    override fun ThucHienCardStackView() {
        viewImp.CardStackView(listData)
    }

    override fun ThucHienReload() {
        viewImp.Reload(listData)
    }

    override fun ThucHienSlidePanel() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun ThucHienRewind() {
    }
}