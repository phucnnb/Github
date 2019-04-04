package com.example.mvpretrofit.Presenter.TrangChu;

import android.content.Context;
import android.util.Log;

import com.example.mvpretrofit.Model.Android;
import com.example.mvpretrofit.Model.User;
import com.example.mvpretrofit.Retrofit.APIUtils;
import com.example.mvpretrofit.Retrofit.DataClient;
import com.example.mvpretrofit.View.TrangChu.ViewXuLyTrangChu;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class PresenterLogicTrangChu implements PresenterImpXuLyTrangChu {

    ViewXuLyTrangChu viewXuLyTrangChu = null;
    Context context;
    CompositeDisposable compositeDisposable;
    List<Android> listAndroid = null;
    List<User> listUser = null;
    DisposableObserver<List<Android>> disposableObserverAndroid;
    private DisposableObserver<List<User>> disposableObserverUser;

    public PresenterLogicTrangChu(ViewXuLyTrangChu viewXuLyTrangChu, Context context) {
        this.viewXuLyTrangChu = viewXuLyTrangChu;
        this.context = context;
    }

    @Override
    public void ThucHienLayDuLieuUser() {
        listUser = new ArrayList();
        compositeDisposable = new CompositeDisposable();

        disposableObserverUser = new DisposableObserver<List<User>>() {
            @Override
            public void onNext(List<User> users) {
                listUser = users;
                viewXuLyTrangChu.LayDuLieuUser(users);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        XuLyDuLieuUser();
    }

    private void XuLyDuLieuUser() {
        DataClient dataClient = APIUtils.getData();
        Disposable disposable = dataClient.loadUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //.subscribe(this::handleResponse,this::handleError,this::handleSuccess);
                .subscribeWith(disposableObserverUser);
        compositeDisposable.add(disposable);
    }

    @Override
    public void ThucHienLayDuLieuAndroid() {
        listAndroid = new ArrayList();
        compositeDisposable = new CompositeDisposable();

        disposableObserverAndroid = new DisposableObserver<List<Android>>() {
            @Override
            public void onNext(List<Android> androids) {
                listAndroid = androids;
                viewXuLyTrangChu.LayDuLieuAndroid(listAndroid);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("BBB",e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        XuLyDuLieuAndroid();
    }

    private void XuLyDuLieuAndroid() {
        DataClient dataClient = APIUtils.getData();
        Disposable disposable = dataClient.register()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //.subscribe(this::handleResponse,this::handleError,this::handleSuccess);
                .subscribeWith(disposableObserverAndroid);
        compositeDisposable.add(disposable);
    }


    @Override
    public void ThucHienXoaView() {
        viewXuLyTrangChu = null;
        if(disposableObserverAndroid != null ){
            disposableObserverAndroid.dispose();
        }
        if(disposableObserverUser != null){
            disposableObserverUser.dispose();
        }
    }

    @Override
    public void ThucHienClearData() {
        if (listUser != null) {
            listUser.clear();
        }
        if (listAndroid != null) {
            listAndroid.clear();
        }
    }
}
