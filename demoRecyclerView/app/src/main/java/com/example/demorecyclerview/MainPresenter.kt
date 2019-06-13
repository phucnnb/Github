package com.example.demorecyclerview

import android.content.Context

class MainPresenter(private var mainView: MainView, private var context: Context) {

    private var listData: ArrayList<Android> = ArrayList()

    fun logicPrepareData(){
        val a1 = Android("1.5","Cupcake","API level 3")
        val a2 = Android("1.6","Donut","API level 4")
        val a3 = Android("2.0 - 2.1","Eclair","API level 5 - 7")
        val a4 = Android("2.2","Froyo","API level 8")
        val a5 = Android("2.3","Gingerbread","API level 9 - 10")
        listData.add(a1)
        listData.add(a2)
        listData.add(a3)
        listData.add(a4)
        listData.add(a5)
        mainView.prepareData(listData)
    }


    fun logicInsertData(listDataRecyclerView: ArrayList<Android>) {


    }

    fun logicUpdateData(){
        mainView.updateData()

    }

    fun logicInsertData() {
        val android = Android("3.0 - 3.2","Honeycomb","API level 11 - 13")
        listData.add(android)
        mainView.insertData()
    }
}