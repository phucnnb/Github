package com.example.loicuabac2.view.category

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.util.Log
import com.example.loicuabac2.Constants
import com.example.loicuabac2.entity.CategoryImage
import com.example.loicuabac2.entity.CategoryStory
import com.example.loicuabac2.service.database.AppDatabase
import com.example.loicuabac2.service.download.DownloadData
import com.example.loicuabac2.service.download.DownloadInterface
import com.example.loicuabac2.service.download.ParseData
import com.example.loicuabac2.service.retrofit.APIUtils
import com.example.loicuabac2.service.retrofit.DataClient
import android.arch.lifecycle.Observer
import com.example.loicuabac2.entity.CategoryStoryOffline
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryPresenter(
    private var categoryView: CategoryView,
    private var context: Context,
    private var downloadInterface: DownloadInterface
) {

    private var idSpecies : Int = 0
    private var isLoaded : Boolean = false

    fun logicLoadMore(totalItem : Int, seemItem : Int, species : String) {
        val itemLoadTruoc : Int
        when (idSpecies) {
            1 -> {
                itemLoadTruoc = 7
                if (!isLoaded && totalItem <= seemItem + itemLoadTruoc) {
                    isLoaded = true
                    getDataStory(species, totalItem)
                }
            }
            2 -> {
                itemLoadTruoc = 2
                if (!isLoaded && totalItem <= seemItem + itemLoadTruoc) {
                    isLoaded = true
                    getImage(species, totalItem)
                }
            }
        }

    }

    fun logicCheckSpecies(species : String) {
        idSpecies = if (species == "3") {
            3
        } else {
            val download = DownloadData (downloadInterface, Constants.URL_SPECIES + species)
            download.execute()
            ParseData().getIdSpecies(download.get())
        }


        when (idSpecies) {
            1 -> getDataStory(species,0)
            2 -> getImage(species,0)
            3 -> getDataOffline()
        }

    }

    private fun getDataOffline(){
        val listData : ArrayList<CategoryStoryOffline> = ArrayList()
        AppDatabase.getInstance(context)?.categoryStoryOfflineDao()?.loadAllStory()?.observe(context as LifecycleOwner,
            Observer { result ->
                if (result != null) {
                    listData.addAll(result)
                    categoryView.updateCategoryStoryOffline(result)
                    Log.d("baophuc", result.toString())
                } else {
                    Log.d("baophuc", "ko có")
                }
            })
    }

    private fun getImage(species: String, count : Int) {
        var listImage : ArrayList<CategoryImage>

        val dataClient : DataClient = APIUtils.data.getData()
        val callImage  = dataClient.image(species, count)

        callImage.enqueue(object : Callback<List<CategoryImage>>{
            override fun onFailure(call: Call<List<CategoryImage>>, t: Throwable) {
                Log.d("Lỗi",t.toString())
            }

            override fun onResponse(call: Call<List<CategoryImage>>, response: Response<List<CategoryImage>>) {
                listImage = response.body() as ArrayList<CategoryImage>
                if (listImage.size > 0) {
                    categoryView.updateCategoryImage(listImage)
                }
                isLoaded = false
            }

        })

    }

    private fun getDataStory(species: String, count : Int) {
        var listStory : ArrayList<CategoryStory>

        val dataClient : DataClient = APIUtils.data.getData()
        val callStrory  = dataClient.story(species, count)

        callStrory.enqueue(object : Callback<List<CategoryStory>>{
            override fun onFailure(call: Call<List<CategoryStory>>, t: Throwable) {
                Log.d("Lỗi",t.toString())
            }

            override fun onResponse(call: Call<List<CategoryStory>>, response: Response<List<CategoryStory>>) {
                listStory = response.body() as ArrayList<CategoryStory>
                if (listStory.size > 0) {
                    categoryView.updateCategoryStory(listStory)
                }
                isLoaded = false
            }

        })
    }
}