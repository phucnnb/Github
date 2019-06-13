package com.example.demorecyclerview

import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class AdapterAndroid: RecyclerView.Adapter<AdapterAndroid.AndroidHolder>{

        private var listData: ArrayList<Android> = ArrayList()
        private var context: Context

    constructor(listData: ArrayList<Android>, context: Context) : super() {
        this.listData = listData
        this.context = context
    }

    fun insertDataNew(listDataNew: ArrayList<Android>){
        Log.d("CCC",listDataNew.toString())
        Log.d("CCC",listData.toString())
        var mainDiffUtit = MainDiffUtilCallBack(listData,listDataNew)
        var diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(mainDiffUtit)
        listData.clear()
        listData.addAll(listDataNew)
        diffResult.dispatchUpdatesTo(this)
    }

    fun updateDataNew(listDataNew: ArrayList<Android>){
        var mainDiffUtit = MainDiffUtilCallBack(listData,listDataNew)
        var diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(mainDiffUtit)

        listData.clear()
        listData.addAll(listDataNew)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AndroidHolder {
        val layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView: View = layoutInflater.inflate(R.layout.item_android,p0,false)
        return AndroidHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(p0: AndroidHolder, p1: Int) {
        val android : Android = listData[p1]

        p0.tvVersion.text = android.getVer()
        p0.tvAPI.text = android.getApi()
        p0.tvName.text = android.getName()
    }

    class AndroidHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_name)
        var tvVersion: TextView = itemView.findViewById(R.id.tv_version)
        var tvAPI: TextView = itemView.findViewById(R.id.tv_api_level)


    }
}