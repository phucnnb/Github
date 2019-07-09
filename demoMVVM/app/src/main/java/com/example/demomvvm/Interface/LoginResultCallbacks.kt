package com.example.demomvvm.Interface

interface LoginResultCallbacks {
    fun onSuccess(message : String)
    fun onError(message : String)
}