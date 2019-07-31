package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var edita : Int
        var editb : Int
        var tvkq : Int

        btn.setOnClickListener {
            edita = a.text.toString().toInt()
            editb = b.text.toString().toInt()
            kq.text = (edita + editb).toString()
        }
    }
}
