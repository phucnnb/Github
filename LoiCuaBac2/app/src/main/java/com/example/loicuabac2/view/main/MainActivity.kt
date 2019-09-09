package com.example.loicuabac2.view.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.loicuabac2.Constants
import com.example.loicuabac2.entity.ChildMenu
import com.example.loicuabac2.entity.MainMenu
import com.example.loicuabac2.view.category.CategoryActivity
import com.example.loicuabac2.view.main.adapter.ChildMenuAdapter
import com.example.loicuabac2.view.main.adapter.MainMenuAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainView, MainMenuAdapter.OnClickMenu {

    private lateinit var logic: MainPresenter
    private var listMainMenu : ArrayList<MainMenu> = ArrayList()
    private var listChildMenu : ArrayList<ChildMenu> = ArrayList()
    private lateinit var adapterMainMenu : MainMenuAdapter
    private lateinit var adapterChildMenu : ChildMenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.loicuabac2.R.layout.activity_main)

        logic = MainPresenter(this,this)
        logic.logicCheckInternet()
        logic.logicPrepareDataMainMenu()
        setDrawerLayout()
        setChildMenu()
    }

    private fun setChildMenu() {
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerChildMenu.layoutManager = layoutManager
        adapterChildMenu = ChildMenuAdapter(this, listChildMenu)
        recyclerChildMenu.adapter = adapterChildMenu
    }

    private fun setDrawerLayout() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = ""

        val actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, com.example.loicuabac2.R.string.open, com.example.loicuabac2.R.string.close)
        actionBarDrawerToggle.syncState()
        toolbar.setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerMainMenu.layoutManager = layoutManager
        adapterMainMenu = MainMenuAdapter(this, listMainMenu)
        adapterMainMenu.setListener(this)
        recyclerMainMenu.adapter = adapterMainMenu
    }

    override fun checkInternet(mes: Int,check : Boolean) {
        if (check) {
            if (listMainMenu.size == 2) {
                listMainMenu.clear()
                logic.logicPrepareDataMainMenu()
                Toast.makeText(applicationContext,mes,Toast.LENGTH_SHORT).show()
            }

        } else {
            if(listMainMenu.size > 2 || listMainMenu.size == 0) {
                listMainMenu.clear()
                listMainMenu.add(MainMenu("5","Giới Thiệu"))
                listMainMenu.add(MainMenu("6","Truyện Đã Tải"))
                adapterMainMenu.notifyDataSetChanged()
                Toast.makeText(applicationContext,mes,Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun prepareDataMainMenu(listDataMenu: ArrayList<MainMenu>) {
        listMainMenu.add(MainMenu("5","Giới Thiệu"))
        listMainMenu.addAll(listDataMenu)
        listMainMenu.add(MainMenu("6","Truyện Đã Tải"))

        adapterMainMenu.notifyDataSetChanged()
    }

    override fun prepareDataChildMenu(listDataChildMenu: ArrayList<ChildMenu>) {
        listChildMenu.clear()
        listChildMenu.addAll(listDataChildMenu)

        adapterChildMenu.notifyDataSetChanged()
    }

    override fun listenClickItem(id: String) {
        logic.logicPrepareDataChildMenu(id)
        drawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun moveStoryOffline(check: Boolean) {
        if(check) {
            val intent = Intent(this,CategoryActivity::class.java)
            intent.putExtra(Constants.SPECIES_CHILD_MENU,"3")
            startActivity(intent)
        } else {
            runOnUiThread { Toast.makeText(applicationContext, com.example.loicuabac2.R.string.no_data, Toast.LENGTH_SHORT).show() }
        }
    }
}
