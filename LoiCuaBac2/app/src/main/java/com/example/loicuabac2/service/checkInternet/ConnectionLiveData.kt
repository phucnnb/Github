package com.example.loicuabac2.service.checkInternet

import android.arch.lifecycle.LiveData
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import com.example.loicuabac2.entity.ConnectionModel
import android.net.NetworkInfo
import android.content.Intent
import com.example.loicuabac2.R


@Suppress("DEPRECATION")
class ConnectionLiveData(private var context: Context) : LiveData<ConnectionModel>() {

    private val networkReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.extras != null) {
                val activeNetwork = intent.extras!!.get(ConnectivityManager.EXTRA_NETWORK_INFO) as NetworkInfo
                val isConnected = activeNetwork.isConnectedOrConnecting
                if (isConnected) {
                    when (activeNetwork.type) {
                        ConnectivityManager.TYPE_WIFI -> postValue(ConnectionModel(R.string.wifi_data, true))
                        ConnectivityManager.TYPE_MOBILE -> postValue(ConnectionModel(R.string.modile_data, true))
                    }
                } else {
                    postValue(ConnectionModel(0, false))
                }
            }
        }
    }
    override fun onActive() {
        super.onActive()
        val filter  = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(networkReceiver,filter)
    }

    override fun onInactive() {
        super.onInactive()
        context.unregisterReceiver(networkReceiver)

    }
}