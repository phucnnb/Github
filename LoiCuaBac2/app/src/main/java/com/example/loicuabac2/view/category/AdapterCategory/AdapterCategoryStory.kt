package com.example.loicuabac2.view.category.AdapterCategory

import android.app.Activity
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
import com.example.loicuabac2.entity.CategoryStory
import com.example.loicuabac2.view.readstory.ReadStoryActivity

class AdapterCategoryStory (private var context: Context?, private var storys: ArrayList<CategoryStory>?):
                    RecyclerView.Adapter<AdapterCategoryStory.CategoryHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CategoryHolder {
        val layoutInflater : LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = layoutInflater.inflate(R.layout.item_category_adapter_story,p0,false)
        return CategoryHolder(itemView)
    }

    override fun getItemCount(): Int {
        return storys?.size!!
    }

    override fun onBindViewHolder(p0 : CategoryHolder, p1 : Int) {
        val storyCategory = storys?.get(p1)
        p0.txtStory.text = storyCategory!!.nameStory

        p0.cardStory.setOnClickListener {
            val intent = Intent(context,ReadStoryActivity::class.java)
            Log.d("baophuc", storyCategory.linkStory + " " + storyCategory.nameStory + " " + storyCategory.idStory)
            intent.putExtra(Constants.NAME_STORY, storyCategory.nameStory)
            intent.putExtra(Constants.URL_STORY, storyCategory.linkStory)
            intent.putExtra(Constants.ID_STORY, storyCategory.idStory)
            intent.putExtra(Constants.CHEKC_STORY_OFF, "online")
            context?.startActivity(intent)
        }
    }

    class CategoryHolder
        (itemView : View?) : RecyclerView.ViewHolder(itemView!!){
            var cardStory : CardView = itemView!!.findViewById(R.id.cardStory)
            var txtStory : TextView = itemView!!.findViewById(R.id.txtStory)
    }

}