package com.example.loicuabac2.view.main

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.content.Context
import android.util.Log
import com.example.loicuabac2.entity.ChildMenu
import com.example.loicuabac2.entity.ConnectionModel
import com.example.loicuabac2.entity.MainMenu
import com.example.loicuabac2.service.checkInternet.ConnectionLiveData
import com.example.loicuabac2.service.retrofit.APIUtils
import com.example.loicuabac2.service.retrofit.DataClient

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.loicuabac2.R
import java.util.*
import kotlin.collections.ArrayList


class MainPresenter(private var viewMain: MainView) {

    fun  logicCheckInternet(context: Context){
        val connectionLiveData = ConnectionLiveData(context)
        connectionLiveData.observe(context as LifecycleOwner, object : Observer<ConnectionModel>{
            override fun onChanged(t: ConnectionModel?) {
                if (t != null) {
                    if (t.isConnrcted) {
                        when (t.type) {
                            R.string.wifi_data -> viewMain.checkInternet(R.string.connected_wifi_data,true)
                            R.string.modile_data -> viewMain.checkInternet(R.string.connected_mobile_data,true)
                        }
                    } else {
                        viewMain.checkInternet(R.string.disconnect,false)
                    }
                }
            }
        })
    }


    fun logicPrepareDataMainMenu(){
        var listMainMenu : ArrayList<MainMenu> = ArrayList()

        val dataClient : DataClient = APIUtils.data.getData()
        val callMainMenu : Call<List<MainMenu>>  = dataClient.titleMenu()

        callMainMenu.enqueue(object : Callback<List<MainMenu>> {
            override fun onResponse(call: Call<List<MainMenu>>, response: Response<List<MainMenu>>) {
                listMainMenu = response.body() as ArrayList<MainMenu>

                if (listMainMenu.size > 0) {
                    viewMain.prepareDataMainMenu(listMainMenu)
                }
            }
            override fun onFailure(call: Call<List<MainMenu>>, t: Throwable) {
                Log.d("AAA",t.toString())
            }

        })
    }

    fun logicPrepareDataChildMenu(id: String) {
        var listChildMenu : ArrayList<ChildMenu>
        val dataClient : DataClient = APIUtils.data.getData()
        when(id) {
            "5" -> Log.d("baophuc","Giới Thiệu")
            "6" -> Log.d("baophuc","Offline")

            else -> {
                val callChildMenu : Call<List<ChildMenu>> = dataClient.childMenu(id)
                callChildMenu.enqueue(object : Callback<List<ChildMenu>> {
                    override fun onFailure(call: Call<List<ChildMenu>>, t: Throwable) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onResponse(call: Call<List<ChildMenu>>, response: Response<List<ChildMenu>>) {
                        listChildMenu = response.body() as ArrayList<ChildMenu>

                        if (listChildMenu.size > 0) {
                            viewMain.prepareDataChildMenu(listChildMenu)
                        }
                    }

                })
            }
        }

    }

//    fun logicPrepareDataMenu(listMainMenu: ArrayList<MainMenu>, listDataHeader: ArrayList<String>) {
//
//        val dataClient : DataClient = APIUtils.data.getData()
//
//        val listDataChild : HashMap<String, List<String>> = HashMap()
//
//
//        for (c in 0 until listMainMenu.size){
//            val callChildMenu : Call<List<ChildMenu>> = dataClient.childMenu(listMainMenu.get(c).id)
//            callChildMenu.enqueue(object : Callback<List<ChildMenu>>{
//                override fun onFailure(call: Call<List<ChildMenu>>, t: Throwable) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//
//                override fun onResponse(call: Call<List<ChildMenu>>, response: Response<List<ChildMenu>>) {
//                    listChildMenu = response.body() as ArrayList<ChildMenu>
//                    val listChild : ArrayList<String> = ArrayList()
//                    for (b in 0 until listChildMenu.size){
//                        listChild.add(listChildMenu.get(b).child)
//                    }
//                    listDataChild[listDataHeader[c]] = listChild
//                    if (c == (listMainMenu.size-1)){
//                        //viewMain.prepareDataMenu(listDataHeader,listDataChild)
//                    }
//                }
//            })
//            try {
//                Thread.sleep(50)
//            } catch (e: InterruptedException) {
//                e.printStackTrace()
//            }
//
//        }
//    }

    /*fun logicPrepareDataMenu(){
        val listDataHeader : ArrayList<String> = ArrayList()
        val listDataChild : HashMap<String, List<String>> = HashMap()
        val listChild : ArrayList<String> = ArrayList()

        val dataClient : DataClient = APIUtils.data.getData()
        val callMainMenu : Call<List<MainMenu>>  = dataClient.titleMenu()
        callMainMenu.enqueue(object : Callback<List<MainMenu>> {
            override fun onResponse(call: Call<List<MainMenu>>, response: Response<List<MainMenu>>) {
                val listMainMenu : ArrayList<MainMenu> = response.body() as ArrayList<MainMenu>

                if (listMainMenu.size > 0){
                    for ( a in 0 until listMainMenu.size){
                        listDataHeader.add(listMainMenu[a].tenMenu)

                        var listChildMenu : ArrayList<ChildMenu> = ArrayList()
                        val callChildMenu : Call<List<ChildMenu>> = dataClient.childMenu(listMainMenu[a].id)
                        callChildMenu.enqueue(object : Callback<List<ChildMenu>>{
                            override fun onResponse(call: Call<List<ChildMenu>>, response: Response<List<ChildMenu>>) {
                                listChildMenu = response.body() as ArrayList<ChildMenu>
                                for (b in 0 until listChildMenu.size){
                                    listChild.add(listChildMenu[b].child)
                                    if (b == (listChildMenu.size-1)){
                                        Log.d("baophuc5", b.toString() + " ---- " + listChildMenu.size )
                                        viewMain.prepareDataMenu(listDataHeader,listDataChild)
                                        Log.d("baophuc4",listDataChild.toString())
                                    }
                                }
                                listDataChild[listDataHeader[a]] = listChild
                                listChild.clear()
                                Log.d("baophuc3",listDataChild.toString())
                            }
                            override fun onFailure(call: Call<List<ChildMenu>>, t: Throwable) {
                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            }

                        })
                    }
                    Log.d("baophuc1",listDataChild.toString())
                }

                Log.d("baophuc",listDataHeader.toString())
                Log.d("baophuc2",listDataChild.toString())

            }

            override fun onFailure(call: Call<List<MainMenu>>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })


    }*/
}