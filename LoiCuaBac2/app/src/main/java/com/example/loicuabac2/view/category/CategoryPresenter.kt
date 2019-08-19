package com.example.loicuabac2.view.category

import android.content.Context
import android.util.Log
import com.example.loicuabac2.Constants
import com.example.loicuabac2.entity.CategoryImage
import com.example.loicuabac2.entity.CategoryStory
import com.example.loicuabac2.service.download.DownloadData
import com.example.loicuabac2.service.download.DownloadInterface
import com.example.loicuabac2.service.download.ParseData
import com.example.loicuabac2.service.retrofit.APIUtils
import com.example.loicuabac2.service.retrofit.DataClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryPresenter (private var categoryView: CategoryView, private var context: Context, private var downloadInterface: DownloadInterface) {

    fun logicCheckSpecies(species : String) {
        Log.d("baophuc",Constants.URL_SPECIES + species + " 00000000000000")
        val download = DownloadData (downloadInterface, Constants.URL_SPECIES + species)
        download.execute()
        val idSpecies : Int = ParseData().getIdSpecies(download.get())
        Log.d("baophuc",idSpecies.toString() + " 00000000000000")

        when (idSpecies) {
            1 -> getDataStory(species,0)
            2 -> getImage(species,0)
        }

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
            }

        })
    }
}