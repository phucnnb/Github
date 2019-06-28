package com.example.demorecyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),MainView {

    private lateinit var logic: MainPresenter
    private lateinit var adapterAndroid: AdapterAndroid
    private lateinit var actionBar: ActionBar
    private lateinit var listData: ArrayList<Android>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbarTitle)
        actionBar = this.supportActionBar!!
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.title = ""
        logicPrepareData()
        logic = MainPresenter(this,this)
        logic.logicPrepareData()

    }

    fun logicPrepareData() {
        listData = ArrayList()
        val a1 = Android("1.5", "Cupcake", "API level 3")
        val a2 = Android("1.6", "Donut", "API level 4")
        val a3 = Android("2.0 - 2.1", "Eclair", "API level 5 - 7")
        val a4 = Android("2.2", "Froyo", "API level 8")
        val a5 = Android("2.3", "Gingerbread", "API level 9 - 10")
        listData.add(a1)
        listData.add(a2)
        listData.add(a3)
        listData.add(a4)
        listData.add(a5)
    }

    override fun prepareData(listData: ArrayList<Android>) {

        val layoutInflater: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutInflater
        adapterAndroid = AdapterAndroid(listData,this)
        recycler.adapter = adapterAndroid
    }

    override fun insertData() {
        val android = Android("3.0 - 3.2","Honeycomb","API level 11 - 13")
        listData.add(android)
        Log.d("BBB",listData.toString())
        Toast.makeText(applicationContext,"Thêm Mới",Toast.LENGTH_SHORT).show()
        adapterAndroid.insertDataNew(listData)

        recycler.smoothScrollToPosition(adapterAndroid.itemCount -1)
    }

    override fun updateData() {
        Toast.makeText(applicationContext,"Cập Nhập",Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.menuInsert -> insertData()
            R.id.menuUpdate -> logic.logicUpdateData()
        }
        return super.onOptionsItemSelected(item)
    }

}
