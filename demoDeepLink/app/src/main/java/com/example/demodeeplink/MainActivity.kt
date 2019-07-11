package com.example.demodeeplink

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*
import android.webkit.WebView



class MainActivity : AppCompatActivity() {

    private lateinit var uri : Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        if(intent.data != null){
            uri = intent.data
            val params : List<String> = uri.pathSegments
            val id : String = params.get(params.size-1)
            Log.d("AAA",id)
            Log.d("AAA",params.toString())
        }

        webview.loadUrl("https://firebasestorage.googleapis.com/v0/b/test-97220.appspot.com/o/DeepLink.html?alt=media&token=b31f1f9a-04fc-4d83-b7cd-6d95f6c2c798")
        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return false
            }
        }
    }
}


