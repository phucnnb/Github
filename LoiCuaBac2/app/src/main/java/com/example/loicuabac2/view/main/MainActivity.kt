package com.example.loicuabac2.view.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ExpandableListView
import android.widget.Toast
import com.example.loicuabac2.R
import com.example.loicuabac2.entity.OfflineMenu
import com.example.loicuabac2.view.main.adapter.ExpandableListAdapter
import com.example.loicuabac2.view.main.adapter.OfflineMenuAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.HashMap

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var logic: MainPresenter
    private lateinit var adapterExpandable : ExpandableListAdapter
    private var listDataHeader : ArrayList<String> = ArrayList()
    private var listDataChild : HashMap<String, List<String>> = HashMap()
    private var listDataOffline : ArrayList<OfflineMenu> = ArrayList()
    private lateinit var adapterOfflineMenu : OfflineMenuAdapter

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

        val title1 : OfflineMenu = OfflineMenu("Truyện Offline")
        val title2 : OfflineMenu = OfflineMenu("Giới Thiệu")
        listDataOffline.add(title1)
        listDataOffline.add(title2)
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerOffline.layoutManager = layoutManager
        adapterOfflineMenu = OfflineMenuAdapter(this, listDataOffline)
        recyclerOffline.adapter = adapterOfflineMenu
    }

    override fun checkInternet(mes: Int,check : Boolean) {
        if(check){
            logic.logicPrepareDataMenu()
        }
        Toast.makeText(applicationContext,mes,Toast.LENGTH_SHORT).show()
    }

    override fun prepareDataMenu(listHeader: ArrayList<String>, listChild: HashMap<String, List<String>>) {
        listDataChild = listChild
        listDataHeader =listHeader
        adapterExpandable = ExpandableListAdapter(this,listDataHeader,listDataChild)
        adapterExpandable.notifyDataSetChanged()
        lvExp.setAdapter(adapterExpandable)
    }
}
