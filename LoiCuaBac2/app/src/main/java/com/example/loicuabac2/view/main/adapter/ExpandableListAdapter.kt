package com.example.loicuabac2.view.main.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.loicuabac2.R
import java.util.ArrayList

class ExpandableListAdapter(
    private var context: Context,
    private var listHeader: ArrayList<String>,
    private var listChild: HashMap<String, List<String>>
) : BaseExpandableListAdapter() {


    override fun getGroup(groupPosition: Int): Any {
       return listHeader[groupPosition]
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
      return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView1: View?, parent: ViewGroup?): View {
        val headerTitle = getGroup(groupPosition)
        var convertView = convertView1
        if(convertView == null){
            val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.item_title_group,null)
        }

        val imageAdd : ImageView = convertView!!.findViewById(R.id.imageAdd)
        val txtTitleHeaders : TextView = convertView!!.findViewById(R.id.txtTitleHeaders)

        if(isExpanded){
            imageAdd.setImageResource(R.drawable.baseline_keyboard_arrow_up_black_16dp)
        }else{
            imageAdd.setImageResource(R.drawable.baseline_keyboard_arrow_down_black_16dp)
            txtTitleHeaders.setTypeface(null,Typeface.BOLD)
            txtTitleHeaders.text = headerTitle.toString()
        }
        return convertView
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return listChild[listHeader[groupPosition]]?.size!!
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
       return listChild[listHeader[groupPosition]]?.get(childPosition)!!

    }

    override fun getGroupId(groupPosition: Int): Long {
         return groupPosition.toLong()
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView1: View?,
        parent: ViewGroup?
    ): View {
        val childTitle = getChild(groupPosition,childPosition)
        var convertView = convertView1

        if (convertView == null){
            val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.item_title_child,null)
        }

        val txtTitleChild : TextView = convertView!!.findViewById(R.id.txtTitleChild)
        txtTitleChild.text = childTitle.toString()

        return convertView
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
       return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return listHeader.size
    }

    fun deleteItem(){
        listHeader.clear()
        listChild.clear()
    }
}