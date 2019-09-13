package com.example.loicuabac2.view.main

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.content.Context
import android.util.Log
import android.view.View
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
import com.example.loicuabac2.entity.CategoryStoryOffline
import com.example.loicuabac2.service.database.AppDatabase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList


class MainPresenter(private var viewMain: MainView, private var context: Context) {

    fun  logicCheckInternet(){
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
        var listMainMenu : ArrayList<MainMenu>

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
            "5" -> {
                viewMain.getIntroduce()
            }
            "6" -> {
                var check : List<CategoryStoryOffline>
                Observable.fromCallable {
                    check = AppDatabase.getInstance(context)?.categoryStoryOfflineDao()!!.checkData()
                    if (check.size > 0) {
                        viewMain.moveStoryOffline(true)
                        Log.d("baophuc","true")
                    } else {
                        viewMain.moveStoryOffline(false)
                        Log.d("baophuc","false")
                    }
                }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({

                    },{
                        Log.d("baophuc",it.toString())
                    })

            }

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
}