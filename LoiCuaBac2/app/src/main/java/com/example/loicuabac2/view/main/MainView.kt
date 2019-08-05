package com.example.loicuabac2.view.main

import java.util.HashMap

interface MainView {
    fun checkInternet(mes : Int, check : Boolean)
    fun prepareDataMenu(
        listDataHeader: ArrayList<String>,
        listDataChild: HashMap<String, List<String>>
    )

}