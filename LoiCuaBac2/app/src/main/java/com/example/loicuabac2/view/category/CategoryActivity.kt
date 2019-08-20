package com.example.loicuabac2.view.category

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.loicuabac2.Constants
import com.example.loicuabac2.R
import com.example.loicuabac2.entity.CategoryImage
import com.example.loicuabac2.entity.CategoryStory
import com.example.loicuabac2.service.download.DownloadInterface
import com.example.loicuabac2.view.category.AdapterCategory.AdapterCategoryImage
import com.example.loicuabac2.view.category.AdapterCategory.AdapterCategoryStory
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity(), CategoryView, DownloadInterface{

    private lateinit var logic : CategoryPresenter
    private lateinit var adapterCategoryStory : AdapterCategoryStory
    private lateinit var adapterCategoryImage : AdapterCategoryImage
    private var listStory : ArrayList<CategoryStory> = ArrayList()
    private var listImage : ArrayList<CategoryImage> = ArrayList()
    private lateinit var layoutManager : RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        var species = ""
        if (intent.getStringExtra(Constants.SPECIES_CHILD_MENU) != null) {
            species = intent.getStringExtra(Constants.SPECIES_CHILD_MENU)
        }
        logic = CategoryPresenter(this,this,this)
        logic.logicCheckSpecies(species)
        layoutManager = LinearLayoutManager(this)
        recyclerCatetory.layoutManager = layoutManager
        adapterCategoryStory = AdapterCategoryStory(this, listStory)
        adapterCategoryImage = AdapterCategoryImage(this,listImage)

        recyclerCatetory.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItem = layoutManager.itemCount
                val seemItem = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                logic.logicLoadMore(totalItem,seemItem,species)
            }
        })
    }

    override fun updateCategoryStory(listCategoryStory: ArrayList<CategoryStory>) {
        recyclerCatetory.adapter = adapterCategoryStory
        listStory.addAll(listCategoryStory)
        adapterCategoryStory.notifyDataSetChanged()
    }

    override fun updateCategoryImage(listCategoryImage: ArrayList<CategoryImage>) {
        recyclerCatetory.adapter = adapterCategoryImage
        listImage.addAll(listCategoryImage)
        adapterCategoryImage.notifyDataSetChanged()
    }

    override fun getStringFormUrl(s: String) {
        Log.d("baophuc",s)
    }
}


