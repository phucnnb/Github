package com.example.loicuabac2.view.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.loicuabac2.R
import com.example.loicuabac2.entity.MainMenu
import com.example.loicuabac2.view.main.adapter.MainMenuAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var logic: MainPresenter
    private var listMainMenu : ArrayList<MainMenu> = ArrayList()
    private lateinit var adapterMainMenu : MainMenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logic = MainPresenter(this)
        logic.logicCheckInternet(this)
        logic.logicPrepareDataMenu()
        setDrawerLayout()
    }

    private fun setDrawerLayout() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = ""

        val actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        actionBarDrawerToggle.syncState()
        toolbar.setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        lvExp.setGroupIndicator(null)
        lvExp.setIndicatorBounds(lvExp.left - 0, lvExp.width)

        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerOffline.layoutManager = layoutManager
        adapterMainMenu = MainMenuAdapter(this, listMainMenu)
        recyclerOffline.adapter = adapterMainMenu
    }

    override fun checkInternet(mes: Int,check : Boolean) {
        if(check){

        }
        Toast.makeText(applicationContext,mes,Toast.LENGTH_SHORT).show()
    }

    override fun prepareDataMenu(listDataMenu: ArrayList<MainMenu>) {
        listMainMenu.add(MainMenu("8","Giới Thiệu"))
        listMainMenu.addAll(listDataMenu)
        listMainMenu.add(MainMenu("9","Truyện Đã Tải"))

        adapterMainMenu.notifyDataSetChanged()
    }
}
