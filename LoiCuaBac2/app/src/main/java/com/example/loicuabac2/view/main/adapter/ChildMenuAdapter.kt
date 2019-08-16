package com.example.loicuabac2.view.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.loicuabac2.R
import com.example.loicuabac2.entity.ChildMenu

class ChildMenuAdapter(private var contexxt: Context?, private var childs: ArrayList<ChildMenu>?) : RecyclerView.Adapter<ChildMenuAdapter.ChildMenuHolder>() {

    private var listener : OnClickChildMenu? = null

    override fun onBindViewHolder(p0: ChildMenuHolder, p1: Int) {
        val childMenu = childs?.get(p1)
        p0.txtTitleChild.text = childMenu!!.child
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ChildMenuHolder {
        val layoutInflater : LayoutInflater = contexxt?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView : View = layoutInflater.inflate(R.layout.item_title_child,p0,false)
        return ChildMenuHolder(itemView)
    }

    override fun getItemCount(): Int {
        return childs!!.size
    }

    class ChildMenuHolder(itemView : View?) : RecyclerView.ViewHolder(itemView!!){
        var txtTitleChild : TextView = itemView!!.findViewById(R.id.txtTitleChild)
    }

    interface OnClickChildMenu{
        fun listenClickItem(id: String)
    }

    fun setListener(listener: OnClickChildMenu) {
        this.listener = listener
    }

}