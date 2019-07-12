package com.example.demomvvm2

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.demomvvm2.databinding.ActivityMainBinding
import com.example.demomvvm2.presenter.Presenter
import com.example.demomvvm2.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var activityMainBinding : ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        viewModel.getResultLogin()?.observe(this, Observer {

            Toast.makeText(applicationContext, " $it", Toast.LENGTH_SHORT).show()

        })

        /*activityMainBinding!!.presenter = object : Presenter{

            override fun login() {
                var name : String = activityMainBinding.editEmail.text.toString()
                var pass : String = activityMainBinding.editPass.text.toString()

                viewModel.loginCall(name,pass)
            }

        }*/
        //android:onClick="@{ (v) -> presenter.login()}"
        btnOK.setOnClickListener {
            var name : String = activityMainBinding.editEmail.text.toString()
            var pass : String = activityMainBinding.editPass.text.toString()

            viewModel.loginCall(name,pass)
        }
    }
}
