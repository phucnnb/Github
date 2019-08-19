package com.example.loicuabac2.view.category.AdapterCategory

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.loicuabac2.R
import com.example.loicuabac2.entity.CategoryImage
import com.squareup.picasso.Picasso

class AdapterCategoryImage (private var context: Context?, private var images: ArrayList<CategoryImage>?):
    RecyclerView.Adapter<AdapterCategoryImage.CategoryImageHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CategoryImageHolder {
        val layoutInflater : LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = layoutInflater.inflate(R.layout.item_category_adapter_image,p0,false)
        return CategoryImageHolder(itemView)
    }

    override fun getItemCount(): Int {
        return images?.size!!
    }

    override fun onBindViewHolder(p0 : CategoryImageHolder, p1 : Int) {
        val imageCategory = images?.get(p1)
        p0.txtImage.text = imageCategory!!.nameImage
        Picasso.get()
            .load(imageCategory.linkImage)
            .into(p0.imageItem)
    }

    class CategoryImageHolder
        (itemView : View?) : RecyclerView.ViewHolder(itemView!!){
        var cardImage : CardView = itemView!!.findViewById(R.id.cardImage)
        var txtImage : TextView = itemView!!.findViewById(R.id.txtImage)
        var imageItem : ImageView = itemView!!.findViewById(R.id.imageItem)
    }

}