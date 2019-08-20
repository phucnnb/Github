package com.example.loicuabac2.view.readstory

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.loicuabac2.Constants
import com.example.loicuabac2.R
import com.example.loicuabac2.service.download.DownLoad
import com.example.loicuabac2.service.download.DownloadData
import com.example.loicuabac2.service.download.DownloadInterface
import kotlinx.android.synthetic.main.activity_read_story.*

class ReadStoryActivity : AppCompatActivity(), DownloadInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_story)
        var linkStory = ""
        var nameStory = ""
        var idStory = ""
        if (intent.getStringExtra(Constants.NAME_STORY) != null) {
            linkStory = intent.getStringExtra(Constants.URL_STORY)
            nameStory = intent.getStringExtra(Constants.NAME_STORY)
            idStory = intent.getStringExtra(Constants.ID_STORY)
        }

        txtTitleStory.text = nameStory

        val download = DownloadData(this,linkStory)
        download.execute()
    }

    override fun getStringFormUrl(s: String) {
        txtBodyStory.text = s
    }
}
