package com.example.demomvvm

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.demomvvm.Interface.LoginResultCallbacks
import com.example.demomvvm.databinding.ActivityMainBinding
import com.example.demomvvm.viewModel.LoginViewModel
import com.example.demomvvm.viewModel.LoginViewModelFactory

class MainActivity : AppCompatActivity(), LoginResultCallbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val activityMainBinding : ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        activityMainBinding.viewModel = ViewModelProviders.of(this,LoginViewModelFactory(this))
            .get(LoginViewModel::class.java)

    }

    override fun onSuccess(message: String) {
        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
    }

    override fun onError(message: String) {
        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
    }
}
