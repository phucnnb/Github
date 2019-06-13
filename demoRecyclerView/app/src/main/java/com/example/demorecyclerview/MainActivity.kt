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
    private lateinit var listDataRecyclerView: ArrayList<Android>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbarTitle)
        actionBar = this!!.supportActionBar!!
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.title = ""

        logic = MainPresenter(this,this)
        logic.logicPrepareData()
        logic.logicAddData()
    }

    override fun prepareData(listData: ArrayList<Android>) {
        listDataRecyclerView = listData
        Log.d("AAA",listData.toString())
    }

    override fun putData(listData: ArrayList<Android>) {
       val layoutInflater: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutInflater
        adapterAndroid = AdapterAndroid(listData,this)
        recycler.adapter = adapterAndroid
    }

    override fun insertData(list: ArrayList<Android>) {
        Log.d("BBB",list.toString())
        Toast.makeText(applicationContext,"Thêm Mới",Toast.LENGTH_SHORT).show()
        adapterAndroid.insertDataNew(list)

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
            R.id.menuInsert -> logic.logicInsertData(listDataRecyclerView)
            R.id.menuUpdate -> logic.logicUpdateData()
        }
        return super.onOptionsItemSelected(item)
    }

}
