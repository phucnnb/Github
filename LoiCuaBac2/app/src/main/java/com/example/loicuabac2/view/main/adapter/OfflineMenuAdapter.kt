package com.example.loicuabac2.view.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.loicuabac2.R
import com.example.loicuabac2.view.main.adapter.OfflineMenuAdapter.*
import com.example.loicuabac2.entity.OfflineMenu

class OfflineMenuAdapter(private var contexxt: Context?, private var titles: ArrayList<OfflineMenu>?) :
    RecyclerView.Adapter<OfflineMenuHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): OfflineMenuHolder {
        val layoutInflater : LayoutInflater = contexxt?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView : View = layoutInflater.inflate(R.layout.item_title_offline,p0,false)
        return OfflineMenuHolder(itemView)
    }

    override fun getItemCount(): Int {
        return titles!!.size
    }

    override fun onBindViewHolder(p0: OfflineMenuHolder, p1: Int) {
        val titleOffline : OfflineMenu = titles!![p1]

        p0.txtTitleOffline.text = titleOffline.title
    }

    class OfflineMenuHolder(itemView : View?): RecyclerView.ViewHolder(itemView!!) {
        var txtTitleOffline : TextView = itemView!!.findViewById(R.id.txtTitleOffline)
    }

}