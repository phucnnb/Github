package com.example.rxandroidretrofit;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.Observer;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recycler;
    private CompositeDisposable compositeDisposable;
    private AdapterAndroid adapterAndroid;
    private List<Android> listAndroid;
    private DisposableObserver<List<Android>> disposableObserver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        compositeDisposable = new CompositeDisposable();
        disposableObserver = new DisposableObserver<List<Android>>() {


            @Override
            public void onNext(List<Android> androids) {
                adapterAndroid = new AdapterAndroid(MainActivity.this,listAndroid);
                recycler.setAdapter(adapterAndroid);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        listAndroid = new ArrayList();

        recycler = findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);



        
        XulyData();
    }

    private void XulyData() {

        DataClient dataClient = APIUtils.getData();
        Disposable disposable = dataClient.register()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(disposableObserver);

        compositeDisposable.add(disposable);
    }
}
