package com.example.demomvvm.viewModel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.demomvvm.Interface.LoginResultCallbacks

class LoginViewModelFactory : ViewModelProvider.NewInstanceFactory {

    private lateinit var loginResultCallbacks : LoginResultCallbacks

    constructor(loginResultCallbacks: LoginResultCallbacks) : super() {
        this.loginResultCallbacks = loginResultCallbacks
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(loginResultCallbacks) as T
    }
}