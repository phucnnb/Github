package com.example.loicuabac2.view.readstory

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.util.Log
import android.view.View
import com.example.loicuabac2.Constants
import com.example.loicuabac2.R
import com.example.loicuabac2.service.download.DownloadData
import com.example.loicuabac2.service.download.DownloadInterface
import com.example.loicuabac2.util.PrefUtil
import kotlinx.android.synthetic.main.activity_read_story.*

class ReadStoryActivity : AppCompatActivity(), DownloadInterface, ReadStoryView {

    private var mBottomSheetBehavior: BottomSheetBehavior<View?>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_story)

        setColor()

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

        iconBack.setOnClickListener {
            super.onBackPressed()
        }

       iconSetting.setOnClickListener {
           val backdropFrag : SettingFragment = SettingFragment()
           backdropFrag.show(supportFragmentManager,"example")
       }

        iconDownload.setOnClickListener {
            Log.d("baophuc","Download")
        }
    }

    private fun setColor() {
        txtBodyStory.textSize = PrefUtil.data.getFloat(this, Constants.BACK_GROUND, Constants.TEXT_SIZE, 16F)
        when(PrefUtil.data.getString(this,Constants.BACK_GROUND,Constants.COLOR_BACK_GROUND,"white")) {

            "white" -> {
                txtBodyStory.setTextColor(resources.getColor(R.color.colorText))
                backGroundScrollView.setBackgroundColor(resources.getColor(R.color.colorWhite))
            }
            "pink" -> {
                txtBodyStory.setTextColor(resources.getColor(R.color.colorText))
                backGroundScrollView.setBackgroundColor(resources.getColor(R.color.colorBackGroundPink))
            }
            "black" -> {
                txtBodyStory.setTextColor(resources.getColor(R.color.colorWhite))
                backGroundScrollView.setBackgroundColor(resources.getColor(R.color.colorBlack))
            }
        }
    }

    override fun getStringFormUrl(s: String) {
        txtBodyStory.text = s
    }

    override fun setColorBackGround() {
        setColor()
    }

    override fun setSizeText(size: Float) {
        txtBodyStory.textSize = size
    }

}
