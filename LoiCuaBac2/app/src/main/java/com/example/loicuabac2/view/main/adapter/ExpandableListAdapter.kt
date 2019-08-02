package com.example.loicuabac2.view.main.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter

class ExpandableListAdapter(
    private var context: Context,
    private var listHeader: List<String>,
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

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
        convertView: View?,
        parent: ViewGroup?
    ): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
       return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return listHeader.size
    }
}