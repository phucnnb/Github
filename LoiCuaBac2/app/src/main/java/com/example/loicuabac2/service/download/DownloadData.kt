package com.example.loicuabac2.service.download

import android.os.AsyncTask
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


class DownloadData (private var downloadInterface : DownloadInterface, private var link : String):
    AsyncTask<String, Void, String>() {

    /*override fun doInBackground(vararg p0: String?): String {
        val url  = URL(link)
        val httpURLConnection = url.openConnection()
        httpURLConnection.connect()

        val inputStream = httpURLConnection.getInputStream()
        val inputStreamReader = InputStreamReader(inputStream)
        val bufferedReader = BufferedReader(inputStreamReader)

        val stringBuilder : StringBuilder = StringBuilder()
        val line = bufferedReader.readLine()

        while (line != null) {
            stringBuilder.append(line + "\n")
        }

        return stringBuilder.toString()
    }*/

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        if (result != null) {
            downloadInterface.getStringFormUrl(result)
        }

    }

    override fun doInBackground(vararg strings: String): String {
        var data = ""

        try {
            val url = URL(link)
            val httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.connect()

            val inputStream : InputStream = httpURLConnection.inputStream
            val inputStreamReader: InputStreamReader = InputStreamReader(inputStream)
            val bufferedReader : BufferedReader = BufferedReader(inputStreamReader)

            val stringBuilder = StringBuilder()
            val line =  bufferedReader.readLine()

            if (line != null) {
                stringBuilder.append(line + "\n")
            }

            data = stringBuilder.toString()
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return data
    }
}