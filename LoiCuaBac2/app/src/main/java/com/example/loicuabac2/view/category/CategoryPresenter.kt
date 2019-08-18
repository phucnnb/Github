package com.example.loicuabac2.view.category

import android.content.Context
import android.util.Log
import com.example.loicuabac2.Constants
import com.example.loicuabac2.service.download.DownloadData
import com.example.loicuabac2.service.download.DownloadInterface
import com.example.loicuabac2.service.download.ParseData

class CategoryPresenter (private var categoryView: CategoryView, private var context: Context, private var downloadInterface: DownloadInterface) {

    fun logicCheckSpecies(species : String) {
        Log.d("baophuc",Constants.URL_SPECIES + species + " 00000000000000")
        val download = DownloadData (downloadInterface, Constants.URL_SPECIES + species)
        download.execute()
        val idSpecies : Int = ParseData().getIdSpecies(download.get())
        Log.d("baophuc",idSpecies.toString() + " 00000000000000")

        when (idSpecies) {
            1 -> getStory()
            2 -> getImage()
        }

    }

    private fun getImage() {
        Log.d("baophuc","hình ảnh")
    }

    private fun getStory() {
        Log.d("baophuc","truyện")
    }
}