package com.example.demofirebase.Install;

import android.app.Application;

import com.example.demofirebase.MainActivity;
import com.google.firebase.FirebaseApp;

public class InstallFireBase extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}
