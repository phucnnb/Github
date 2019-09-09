package com.example.loicuabac2.view.readstory

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.loicuabac2.Constants
import com.example.loicuabac2.R
import com.example.loicuabac2.entity.CategoryStoryOffline
import com.example.loicuabac2.service.database.AppDatabase
import com.example.loicuabac2.service.download.DownloadData
import com.example.loicuabac2.service.download.DownloadInterface
import com.example.loicuabac2.util.PrefUtil
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_read_story.*

class ReadStoryActivity : AppCompatActivity(), DownloadInterface, ReadStoryView {

    private var linkStory = ""
    private var nameStory = ""
    private var idStory = ""
    private var bodyStory = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_story)

        setColor()


        if (intent.getStringExtra(Constants.NAME_STORY) != null) {
            if (intent.getStringExtra(Constants.CHEKC_STORY_OFF) == "offline") {
                bodyStory = intent.getStringExtra(Constants.BODY_STORY)
                nameStory = intent.getStringExtra(Constants.NAME_STORY)
                idStory = intent.getStringExtra(Constants.ID_STORY)
                iconDownload.visibility = View.GONE
                txtBodyStory.text = bodyStory
            } else {
                linkStory = intent.getStringExtra(Constants.URL_STORY)
                nameStory = intent.getStringExtra(Constants.NAME_STORY)
                idStory = intent.getStringExtra(Constants.ID_STORY)

                val download = DownloadData(this,linkStory)
                download.execute()
            }

        }

        txtTitleStory.text = nameStory
        iconBack.setOnClickListener {
            super.onBackPressed()
        }

        iconSetting.setOnClickListener {
            val backdropFrag = SettingFragment()
            backdropFrag.show(supportFragmentManager,"example")
        }

        iconDownload.setOnClickListener {
            saveStory()
        }
    }

    private fun saveStory() {
        val categoryStoryOffline =  CategoryStoryOffline()
        categoryStoryOffline.code = idStory
        categoryStoryOffline.nameStory = nameStory
        categoryStoryOffline.bodyStory = txtBodyStory.text.toString()

        AppDatabase.getInstance(this)?.categoryStoryOfflineDao()?.loadStory((categoryStoryOffline.code).toInt())?.observe(this as LifecycleOwner,
            Observer { result ->
                if (result != null) {
                    Toast.makeText(applicationContext,R.string.had_data,Toast.LENGTH_SHORT).show()
                } else {
                    Observable.fromCallable {
                        AppDatabase.getInstance(this)?.categoryStoryOfflineDao()?.insertStory(categoryStoryOffline)
                    }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            Toast.makeText(applicationContext,R.string.download_done,Toast.LENGTH_SHORT).show()
                        },{
                            Log.d("baophuc",it.toString())
                        })
                }
            })


//        val x = object : AsyncTask<Void,Void,Int>() {
//            override fun doInBackground(vararg params: Void?): Int {
//                try {
//                    AppDatabase.getInstance(applicationContext)?.categoryStoryOfflineDao()?.insertStory(categoryStoryOffline)
//                } catch (e : Exception){
//                    Log.d("eurros",e.toString())
//                }
//
//                return 0
//            }
//
//        }
//
//        x.execute()
    }

    private fun setColor() {
        txtBodyStory.textSize = PrefUtil.data.getFloat(this, Constants.BACK_GROUND, Constants.TEXT_SIZE, 16F)
        when(PrefUtil.data.getString(this,Constants.BACK_GROUND,Constants.COLOR_BACK_GROUND,"white")) {

            "white" -> {
                txtBodyStory.setTextColor(ContextCompat.getColor(this, R.color.colorText))
                backGroundScrollView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))
            }
            "pink" -> {
                txtBodyStory.setTextColor(ContextCompat.getColor(this, R.color.colorText))
                backGroundScrollView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackGroundPink))
            }
            "black" -> {
                txtBodyStory.setTextColor(ContextCompat.getColor(this, R.color.colorWhite))
                backGroundScrollView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBlack))
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
