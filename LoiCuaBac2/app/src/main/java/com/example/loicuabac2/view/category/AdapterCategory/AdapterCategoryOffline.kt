package com.example.loicuabac2.view.category.AdapterCategory

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.loicuabac2.Constants
import com.example.loicuabac2.R
import com.example.loicuabac2.entity.CategoryStoryOffline
import com.example.loicuabac2.view.readstory.ReadStoryActivity

class AdapterCategoryOffline (private var context: Context?, private var storyOffs: ArrayList<CategoryStoryOffline>?):
    RecyclerView.Adapter<AdapterCategoryOffline.CategoryOfflineHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CategoryOfflineHolder {
        val layoutInflater : LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = layoutInflater.inflate(R.layout.item_category_adapter_story,p0,false)
        return CategoryOfflineHolder(itemView)
    }

    override fun getItemCount(): Int {
        return storyOffs?.size!!
    }

    override fun onBindViewHolder(p0 : CategoryOfflineHolder, p1 : Int) {
        val storyCategoryOff = storyOffs?.get(p1)
        p0.txtStory.text = storyCategoryOff!!.nameStory

        p0.cardStory.setOnClickListener {
            val intent = Intent(context, ReadStoryActivity::class.java)
            Log.d("baophuc", storyCategoryOff.nameStory + " " + storyCategoryOff.id)
            intent.putExtra(Constants.NAME_STORY, storyCategoryOff.nameStory)
            intent.putExtra(Constants.BODY_STORY, storyCategoryOff.bodyStory)
            intent.putExtra(Constants.ID_STORY, storyCategoryOff.id.toString())
            intent.putExtra(Constants.CHEKC_STORY_OFF, "offline")
            context?.startActivity(intent)

        }
    }

    class CategoryOfflineHolder
        (itemView : View?) : RecyclerView.ViewHolder(itemView!!){
        var cardStory : CardView = itemView!!.findViewById(R.id.cardStory)
        var txtStory : TextView = itemView!!.findViewById(R.id.txtStory)
    }

}