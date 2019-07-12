package com.example.demomvvm2.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.util.Log
import java.util.*

class LoginViewModel : ViewModel{

    var userName = ObservableField("")
    var userPass = ObservableField("")

    var resultdata = MutableLiveData<String>()

    constructor() : super()

    fun loginCall(userName: String, userPass: String){
        Log.d("AAA",userName + "----" + userPass + "aaaaaaaaaaaa")
        var resultLogin: String = ""
        this.userName.set(userName)
        this.userPass.set(userPass)

        if (userName.equals("123") && userPass.equals("123")){
            resultLogin = "Success"
        }else{

            resultLogin = "Error"
        }

        resultdata.value = resultLogin
    }

    fun getResultLogin() : MutableLiveData<String>? {
        return resultdata
    }
}