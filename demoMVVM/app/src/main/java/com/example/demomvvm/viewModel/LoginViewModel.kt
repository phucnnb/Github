package com.example.demomvvm.viewModel

import android.arch.lifecycle.ViewModel
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.demomvvm.Interface.LoginResultCallbacks
import com.example.demomvvm.model.User


class LoginViewModel: ViewModel {

    private var user : User
    private var loginResultCallbacks : LoginResultCallbacks

    constructor(loginResultCallbacks: LoginResultCallbacks) : super() {
        this.user = User()
        this.loginResultCallbacks = loginResultCallbacks
    }

    fun getEmailText() : TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(p0: Editable?){
                user.setEmail(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        }


    }

    fun getPasswordText() : TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(p0: Editable?){
                user.setPassword(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        }


    }

    fun onLoginClicked(view: View){
        if(user.isValidData()){
            loginResultCallbacks.onSuccess("Success")
        }else{
            loginResultCallbacks.onError("Error")
        }
    }
}