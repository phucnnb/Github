package com.example.democardslide

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.yuyakaido.android.cardstackview.CardStackLayoutManager

class SwipeStackAdapter : RecyclerView.Adapter<SwipeStackAdapter.ItemHolder>{

    private var context: Context? = null
    private var items: List<Item>? = null

    constructor(context: Context?, items: List<Item>) : super() {
        this.context = context
        this.items = items
    }

    fun setList(items: List<Item>){
        this.items = items
    }


    class ItemHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {
        var imageItem : ImageView = itemView!!.findViewById(R.id.imageItem)
        var txtTitle : TextView = itemView!!.findViewById(R.id.txtTitle)
        var txtSubstance : TextView = itemView!!.findViewById(R.id.txtSubstance)


    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ItemHolder {
        val layoutInflater : LayoutInflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView : View = layoutInflater.inflate(R.layout.layout_item,p0,false)
        return ItemHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    override fun onBindViewHolder(holder: ItemHolder, p1: Int) {
        val item : Item = items!![p1]
        holder.imageItem.setImageResource(item.getLinkImage())
        holder.txtTitle.text = item.getTitle()
        holder.txtSubstance.text = item.getSubstance()

    }
}