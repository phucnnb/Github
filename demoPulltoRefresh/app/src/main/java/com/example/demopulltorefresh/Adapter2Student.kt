package com.example.demopulltorefresh

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.daimajia.swipe.SwipeLayout
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter
import com.daimajia.swipe.implments.SwipeItemRecyclerMangerImpl



class Adapter2Student : RecyclerSwipeAdapter<Adapter2Student.Student2Holder> {

    override fun getSwipeLayoutResourceId(position: Int): Int {
        return R.id.swipe2
    }

    private var context: Context
    private var listData: ArrayList<Student>
    private var listener: Update? = null

    constructor(context: Context, listData: ArrayList<Student>) : super() {
        this.context = context
        this.listData = listData
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Student2Holder {
        val layoutInflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView : View = layoutInflater.inflate(R.layout.item_student_2,p0,false)
        return Student2Holder(itemView)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(p0: Student2Holder, p1: Int) {
        val student :Student = listData[p1]
        p0.tvName.text = student.getName().toString()
        val expanded = student.getExpanded()
        if(expanded!!){
            p0.viewLinear.visibility = View.VISIBLE

        } else {
            p0.viewLinear.visibility = View.GONE
        }
        p0.tvNumber.text = student.getNumber().toString()
        p0.tvBirthday.text = student.getBirthday().toString()


        val color = Color.parseColor(student.getColor().toString())
        p0.itemLinear.setBackgroundColor(color)
       /*p0.itemLinear.setOnClickListener {
            val expanded = student.getExpanded()
            student.setExpanded(!expanded!!)
            for(it in 0 until listData.size){
                if(it != p1){
                    listData[it].setExpanded(false)
                    listener?.updateData()
                }
            }
            notifyItemChanged(p1)
        }*/
        p0.swipe2.showMode = SwipeLayout.ShowMode.PullOut
        p0.swipe2.addDrag(SwipeLayout.DragEdge.Right, p0.swipe2.findViewById(R.id.swipeLeft))
        p0.swipe2.addDrag(SwipeLayout.DragEdge.Left, p0.swipe2.findViewById(R.id.swipeRight))

        mItemManger.bindView(p0.itemView,p1)

        p0.swipe2.addSwipeListener(object : SwipeLayout.SwipeListener {
            override fun onStartOpen(layout: SwipeLayout) {
                Log.d("AAA","onStartOpen")
            }

            override fun onOpen(layout: SwipeLayout) {
                Log.d("AAA","onOpen")
            }

            override fun onStartClose(layout: SwipeLayout) {
                Log.d("AAA","onStartClose")
            }

            override fun onClose(layout: SwipeLayout) {
                Log.d("AAA","onClose")
            }

            override fun onUpdate(layout: SwipeLayout, leftOffset: Int, topOffset: Int) {
                Log.d("AAA","onUpdate")
            }

            override fun onHandRelease(layout: SwipeLayout, xvel: Float, yvel: Float) {
                Log.d("AAA","onHandRelease")
            }
        })

        p0.btnDetele2.setOnClickListener {
            Toast.makeText(context,"DETELE",Toast.LENGTH_SHORT).show()
        }

        p0.tvInfo.setOnClickListener {
            Toast.makeText(context,"INFO ",Toast.LENGTH_SHORT).show()
        }
    }

    class Student2Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var tvName : TextView = itemView.findViewById(R.id.tvName2)
        var tvNumber : TextView = itemView.findViewById(R.id.tvNumber2)
        var tvBirthday : TextView = itemView.findViewById(R.id.tvBirthday2)
        var itemLinear : CardView = itemView.findViewById(R.id.itemLinear2)
        var viewLinear : LinearLayout = itemView.findViewById(R.id.viewLinear2)
        var swipe2 : SwipeLayout = itemView.findViewById(R.id.swipe2)
        var swipeLeft : LinearLayout = itemView.findViewById(R.id.swipeLeft)
        var swipeRight : LinearLayout = itemView.findViewById(R.id.swipeRight)
        var btnDetele2 : Button = itemView.findViewById(R.id.btnDelete2)
        var tvInfo : TextView = itemView.findViewById(R.id.tvInfo)
    }

    interface Update{
        fun updateData()
    }

    fun setListener(listener: Update) {
        this.listener = listener
    }
}


