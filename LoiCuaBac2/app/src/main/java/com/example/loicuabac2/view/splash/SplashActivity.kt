package com.example.loicuabac2.view.splash

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.arch.lifecycle.Observer
import com.example.loicuabac2.Constants
import com.example.loicuabac2.R
import com.example.loicuabac2.service.database.AppDatabase
import com.example.loicuabac2.view.main.MainActivity

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        AppDatabase.getInstance(this)?.categoryStoryOfflineDao()?.loadAllStory()?.observe(this,
            Observer { result ->
                if (result != null) {
                    //Log.d("baophuc", result.bodyStory.toString())
                    //listdata.add(result)
                    Log.d("baophuc", result.toString())
                } else {
                    Log.d("baophuc", "ko có")
                }
            })



        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        },Constants.SPLASH_TIME)
    }
}
