package com.example.demopulltorefresh

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

class AdapterStudent : RecyclerView.Adapter<AdapterStudent.StudentHolder> {

    private var context: Context
    private var listData: ArrayList<Student>
    private var listener: Update? = null

    constructor(context: Context, listData: ArrayList<Student>) : super() {
        this.context = context
        this.listData = listData
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): StudentHolder {
        val layoutInflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView : View = layoutInflater.inflate(R.layout.item_student,p0,false)
        return StudentHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(p0: StudentHolder, p1: Int) {
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
        p0.itemLinear.setOnClickListener {
            val expanded = student.getExpanded()
            student.setExpanded(!expanded!!)
            for(it in 0 until listData.size){
                if(it != p1){
                    listData[it].setExpanded(false)
                    listener?.updateData()
                }
            }
            notifyItemChanged(p1)
        }
    }

    class StudentHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var tvName : TextView = itemView.findViewById(R.id.tvName)
        var tvNumber : TextView = itemView.findViewById(R.id.tvNumber)
        var tvBirthday : TextView = itemView.findViewById(R.id.tvBirthday)
        var itemLinear : CardView = itemView.findViewById(R.id.itemLinear)
        var viewLinear : LinearLayout = itemView.findViewById(R.id.viewLinear)
    }

    interface Update{
        fun updateData()
    }

    fun setListener(listener: Update) {
        this.listener = listener
    }
}