package com.example.demohandlingimage

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageFirst.drawable

        val bitmap : Bitmap? = (imageFirst.drawable as BitmapDrawable).bitmap

        imageSecond.setImageBitmap(bitmap)


    }
}
