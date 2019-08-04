package com.example.loicuabac2.view.main

import java.util.HashMap

interface MainView {
    fun checkInternet(mes : String)
    fun prepareDataMenu(
        listDataHeader: ArrayList<String>,
        listDataChild: HashMap<String, List<String>>
    )

}