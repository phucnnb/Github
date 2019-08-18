package com.example.loicuabac2.view.category

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.loicuabac2.Constants
import com.example.loicuabac2.R
import com.example.loicuabac2.service.download.DownloadInterface

class CategoryActivity : AppCompatActivity(), CategoryView, DownloadInterface{

    private var species : String = ""
    private lateinit var logic : CategoryPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        if (intent.getStringExtra(Constants.SPECIES_CHILD_MENU) != null) {
            species = intent.getStringExtra(Constants.SPECIES_CHILD_MENU)
            Log.d("baophuc", species + " 1111111111")
        }

        Log.d("baophuc", species + " 1111111111")

        logic = CategoryPresenter(this,this,this)
        logic.logicCheckSpecies(species)


    }

    override fun getStringFormUrl(s: String) {
        Log.d("baophuc",s)
    }
}


