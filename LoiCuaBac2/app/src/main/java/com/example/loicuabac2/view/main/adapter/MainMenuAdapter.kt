package com.example.loicuabac2.view.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.loicuabac2.R
import com.example.loicuabac2.view.main.adapter.MainMenuAdapter.*
import com.example.loicuabac2.entity.MainMenu

class MainMenuAdapter(private var contexxt: Context?, private var titles: ArrayList<MainMenu>?) :
    RecyclerView.Adapter<MainMenuHolder>() {

    private var listener : onClick? = null


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MainMenuHolder {
        val layoutInflater : LayoutInflater = contexxt?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView : View = layoutInflater.inflate(R.layout.item_title_offline,p0,false)
        return MainMenuHolder(itemView)
    }

    override fun getItemCount(): Int {
        return titles!!.size
    }

    override fun onBindViewHolder(p0: MainMenuHolder, p1: Int) {
        val mainMenu : MainMenu = titles!![p1]

        p0.txtTitleOffline.text = mainMenu.tenMenu
        p0.itemMenu.setOnClickListener {
            listener?.listenClickItem(mainMenu.id)
        }
    }

    class MainMenuHolder(itemView : View?): RecyclerView.ViewHolder(itemView!!) {
        var txtTitleOffline : TextView = itemView!!.findViewById(R.id.txtTitleOffline)
        var itemMenu : LinearLayout = itemView!!.findViewById(R.id.itemMenu)
    }

    interface onClick{
        fun listenClickItem(id: String)
    }

    fun setListener(listener: onClick) {
        this.listener = listener
    }

}