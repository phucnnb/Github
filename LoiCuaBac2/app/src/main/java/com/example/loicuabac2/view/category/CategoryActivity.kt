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

    private var species : String = ""
    private lateinit var logic : CategoryPresenter
    private lateinit var adapterCategoryStory : AdapterCategoryStory
    private lateinit var adapterCategoryImage : AdapterCategoryImage
    private var listStory : ArrayList<CategoryStory> = ArrayList()
    private var listImage : ArrayList<CategoryImage> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        if (intent.getStringExtra(Constants.SPECIES_CHILD_MENU) != null) {
            species = intent.getStringExtra(Constants.SPECIES_CHILD_MENU)
        }
        logic = CategoryPresenter(this,this,this)
        logic.logicCheckSpecies(species)
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerCatetory.layoutManager = layoutManager
        adapterCategoryStory = AdapterCategoryStory(this, listStory)
        adapterCategoryImage = AdapterCategoryImage(this,listImage)

        loadDataStory()
    }

    private fun loadDataStory() {
        recyclerCatetory.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    override fun updateCategoryStory(listCategoryStory: ArrayList<CategoryStory>) {
        recyclerCatetory.adapter = adapterCategoryStory
        listStory.clear()
        listStory.addAll(listCategoryStory)
        adapterCategoryStory.notifyDataSetChanged()
    }

    override fun updateCategoryImage(listCategoryImage: ArrayList<CategoryImage>) {
        recyclerCatetory.adapter = adapterCategoryImage
        listImage.clear()
        listImage.addAll(listCategoryImage)
        adapterCategoryImage.notifyDataSetChanged()
    }

    override fun getStringFormUrl(s: String) {
        Log.d("baophuc",s)
    }
}


