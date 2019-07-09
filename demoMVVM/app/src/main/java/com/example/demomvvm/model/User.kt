package com.example.demomvvm.model

import android.databinding.BaseObservable
import android.text.TextUtils
import android.util.Log
import android.util.Patterns

class User : BaseObservable{


    private var email : String = ""
    private var password: String = ""

    constructor(email: String, password: String) : super() {
        this.email = email
        this.password = password
    }

    constructor()

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun isValidData() : Boolean {
        val textEmail:String
        val textPass:String
        if (!TextUtils.isEmpty(getEmail()) &&
            Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches() &&
            getPassword()!!.length > 3){
            textEmail = getEmail().toString()
            textPass = getPassword().toString()
            Log.d("AAA","AAAAAA")
            return textEmail == "baophuc@gmail.com" && textPass == "12345"
        }else
            Log.d("AAA","BBBBBB")
            return false



        //
    }
}