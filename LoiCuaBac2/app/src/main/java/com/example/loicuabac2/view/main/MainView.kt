package com.example.loicuabac2.view.main

import com.example.loicuabac2.entity.ChildMenu
import com.example.loicuabac2.entity.MainMenu

interface MainView {
    fun checkInternet(mes : Int, check : Boolean)
    fun prepareDataMainMenu(listDataMenu: ArrayList<MainMenu>)
    fun prepareDataChildMenu(listDataChildMenu: ArrayList<ChildMenu>)

}