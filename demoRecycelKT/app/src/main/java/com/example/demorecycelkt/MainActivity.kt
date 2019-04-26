package com.example.demorecycelkt

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var listSV : List<SinhVien>? = null
    private var adapterSV : AdapterSinhVien? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prepareData()
        Log.d("AAA",listSV.toString())

        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutManager
        adapterSV = AdapterSinhVien(this, this.listSV!!)
        recycler.adapter = adapterSV
    }

    private fun prepareData() {
        listSV = ArrayList()
        val sv1 = SinhVien("Nguyễn Văn A",1995)
        val sv2 = SinhVien("Nguyễn Văn B",1994)
        val sv3 = SinhVien("Nguyễn Văn C",1997)
        val sv4 = SinhVien("Nguyễn Văn D",1992)
        val sv5 = SinhVien("Nguyễn Văn E",1999)
        val sv6 = SinhVien("Nguyễn Văn A",1995)
        val sv7 = SinhVien("Nguyễn Văn B",1994)
        val sv8 = SinhVien("Nguyễn Văn C",1997)
        val sv9 = SinhVien("Nguyễn Văn D",1992)
        val sv10= SinhVien("Nguyễn Văn E",1999)
        (listSV as ArrayList<SinhVien>).add(sv1)
        (listSV as ArrayList<SinhVien>).add(sv2)
        (listSV as ArrayList<SinhVien>).add(sv3)
        (listSV as ArrayList<SinhVien>).add(sv4)
        (listSV as ArrayList<SinhVien>).add(sv5)
        (listSV as ArrayList<SinhVien>).add(sv6)
        (listSV as ArrayList<SinhVien>).add(sv7)
        (listSV as ArrayList<SinhVien>).add(sv8)
        (listSV as ArrayList<SinhVien>).add(sv9)
        (listSV as ArrayList<SinhVien>).add(sv10)

    }
}
