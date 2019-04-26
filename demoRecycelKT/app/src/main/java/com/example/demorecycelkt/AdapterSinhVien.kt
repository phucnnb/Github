package com.example.demorecycelkt

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast


class AdapterSinhVien : RecyclerView.Adapter<AdapterSinhVien.SinhVienHolder>{

    private var context: Context? = null
    private var sinhViens: List<SinhVien>? = null

    constructor(context: Context?, sinhViens: List<SinhVien>) : super() {
        this.context = context
        this.sinhViens = sinhViens
    }


    class SinhVienHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {
        var txtHoten : TextView = itemView!!.findViewById(R.id.txtHoTen)
        var txtNamSinh : TextView = itemView!!.findViewById(R.id.txtNamSinh)
        var itemSinhVien : LinearLayout = itemView!!.findViewById(R.id.itemSinhVien)

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SinhVienHolder {
        val layoutInflater : LayoutInflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView : View = layoutInflater.inflate(R.layout.item_sinhvien,p0,false)
        return SinhVienHolder(itemView)
    }

    override fun getItemCount(): Int {
        return sinhViens!!.size
    }

    override fun onBindViewHolder(holder: SinhVienHolder, p1: Int) {
        val sv : SinhVien = sinhViens!![p1]

        holder.txtHoten.text = sv.getHoTen()
        holder.txtNamSinh.text = sv.getNamSinh().toString()
        holder.itemSinhVien.setOnClickListener{
            Toast.makeText(context,"Họ tên: " + sv.getHoTen() + "\n Năm Sinh: " + sv.getNamSinh(),Toast.LENGTH_LONG).show()
        }



    }

}