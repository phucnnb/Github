package com.example.demorxandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Main2Activity extends AppCompatActivity {
     private CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Observable<String> animal_13 = Observable.fromArray("Ant", "Ape",
                "Bat", "Bee", "Bear", "Butterfly",
                "Cat", "Crab", "Cod",
                "Dog", "Dove",
                "Fox", "Frog");

        DisposableObserver<String> ngQuanSat_1 = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                Log.d("AAA", "Người Quan Sát 1: " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("AAA","==============Nguoi 1==============");
            }
        };

        DisposableObserver<String> ngQuanSat_2 = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                Log.d("AAA", "Người Quan Sát 2: " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("AAA","==============Nguoi 2==============");
            }
        };


        compositeDisposable.add(animal_13.subscribeOn(Schedulers.io())
                                         .observeOn(AndroidSchedulers.mainThread())
                                         .filter(new Predicate<String>() {
                                             @Override
                                             public boolean test(String s) throws Exception {
                                                 return  s.length()<4;
                                             }
                                         })
                                         .subscribeWith(ngQuanSat_1));

        compositeDisposable.add(animal_13.subscribeOn(Schedulers.io())
                                         .observeOn(AndroidSchedulers.mainThread())
                                         .filter(new Predicate<String>() {
                                             @Override
                                             public boolean test(String s) throws Exception {
                                                 return s.length() > 3;
                                             }
                                         })
                                         .map(new Function<String, String>() {
                                             @Override
                                             public String apply(String s) throws Exception {
                                                 return s.toUpperCase();
                                             }
                                         })
                                         .subscribeWith(ngQuanSat_2))  ;





    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

}
