package com.example.loicuabac2.view.main

import android.util.Log
import com.example.loicuabac2.entity.ChildMenu
import com.example.loicuabac2.entity.MainMenu
import com.example.loicuabac2.service.retrofit.APIUtils
import com.example.loicuabac2.service.retrofit.DataClient

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class MainPresenter  {

    fun logicPrepareDataMenu(){
        var listDataHeader : ArrayList<String> = ArrayList()
        var listDataChild : HashMap<String, List<String>> = HashMap()
        val listChild : ArrayList<String> = ArrayList()

        val dataClient : DataClient = APIUtils.data.getData()
        val callMainMenu : retrofit2.Call<List<MainMenu>>  = dataClient.titleMenu()
        callMainMenu.enqueue(object : Callback<List<MainMenu>> {
            override fun onResponse(call: Call<List<MainMenu>>, response: Response<List<MainMenu>>) {
               val listMainMenu : ArrayList<MainMenu> = response.body() as ArrayList<MainMenu>

                if (listMainMenu.size > 0){
                    for ( a in 0 until listMainMenu.size){
                        listDataHeader.add(listMainMenu[a].tenMenu)

                        var listChildMenu : ArrayList<ChildMenu> = ArrayList()
                        val callChildMenu : retrofit2.Call<List<ChildMenu>> = dataClient.childMenu(listMainMenu[a].id)
                        callChildMenu.enqueue(object : Callback<List<ChildMenu>>{
                            override fun onResponse(call: Call<List<ChildMenu>>, response: Response<List<ChildMenu>>) {
                                listChildMenu = response.body() as ArrayList<ChildMenu>
                                for (b in 0 until listChildMenu.size){
                                    listChild.add(listChildMenu[b].child)
                                }
                                listDataChild[listDataHeader[a]] = listChild
                            }
                            override fun onFailure(call: Call<List<ChildMenu>>, t: Throwable) {
                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            }

                        })
                    }
                    Log.d("baophuc",listDataChild.toString())
                }
                Log.d("baophuc",listDataHeader.toString())

            }

            override fun onFailure(call: Call<List<MainMenu>>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })


    }
}