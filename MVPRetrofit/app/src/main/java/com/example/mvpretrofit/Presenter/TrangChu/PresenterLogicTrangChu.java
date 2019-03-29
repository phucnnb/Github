package com.example.mvpretrofit.Presenter.TrangChu;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.mvpretrofit.Adapter.AdapterAndroid;
import com.example.mvpretrofit.Adapter.AdapterUser;
import com.example.mvpretrofit.Model.Android;
import com.example.mvpretrofit.Model.User;
import com.example.mvpretrofit.Retrofit.APIUtils;
import com.example.mvpretrofit.Retrofit.DataClient;
import com.example.mvpretrofit.View.DangNhap.MainActivity;
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
    AdapterAndroid adapterAndroid;
    AdapterUser adapterUser;
    List<Android> listAndroid;
    List<User> listUser;
    DisposableObserver<List<Android>> disposableObserverAndroid;
    DisposableObserver<List<User>> disposableObserverUser;

    public PresenterLogicTrangChu(ViewXuLyTrangChu viewXuLyTrangChu, Context context) {
        this.viewXuLyTrangChu = viewXuLyTrangChu;
        this.context = context;
    }

    @Override
    public void ThucHienLayDuLieuUser(final RecyclerView recycler) {
        viewXuLyTrangChu.LayDuLieuUser();
        listUser = new ArrayList();
        compositeDisposable = new CompositeDisposable();

        disposableObserverUser = new DisposableObserver<List<User>>() {
            @Override
            public void onNext(List<User> users) {
                for(int i= 0; i < users.size(); i ++ ){
                    listUser.add(users.get(i));
                    Log.d("DDD",users.get(i).getTaikhoan());
                    Log.d("DDD",users.get(i).getMatkhau());
                }

                adapterUser = new AdapterUser(context,listUser);
                recycler.setAdapter(adapterAndroid);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recycler.setLayoutManager(layoutManager);

        XuLyDuLieuUser();

    }

    private void XuLyDuLieuUser() {
        DataClient dataClient = APIUtils.getData();
        Log.d("AAA",dataClient.toString());
        Disposable disposable = dataClient.loadUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //.subscribe(this::handleResponse,this::handleError,this::handleSuccess);
                .subscribeWith(disposableObserverUser);
        Log.d("AAA",disposable.toString());
        compositeDisposable.add(disposable);
    }

    @Override
    public void ThucHienLayDuLieuAndroid(final RecyclerView recycler) {
        viewXuLyTrangChu.LayDuLieuAndroid();
        listAndroid = new ArrayList();
        compositeDisposable = new CompositeDisposable();


        disposableObserverAndroid = new DisposableObserver<List<Android>>() {
            @Override
            public void onNext(List<Android> androids) {
                for(int i= 0; i < androids.size(); i ++ ){
                    listAndroid.add(androids.get(i));
                    Log.d("BBBB","BBBBBB" + listAndroid.toString());
                }

                adapterAndroid = new AdapterAndroid(context,listAndroid);
                recycler.setAdapter(adapterAndroid);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("BBB",e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {

            }
        };

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recycler.setLayoutManager(layoutManager);

        XuLyDuLieuAndroid();


    }

    private void XuLyDuLieuAndroid() {
        DataClient dataClient = APIUtils.getData();
        Log.d("AAA",dataClient.toString());
        Disposable disposable = dataClient.register()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //.subscribe(this::handleResponse,this::handleError,this::handleSuccess);
                .subscribeWith(disposableObserverAndroid);
        Log.d("AAA",disposable.toString());
        compositeDisposable.add(disposable);
    }

    @Override
    public void ThucHienRecyclerView() {
        viewXuLyTrangChu.XuLyRecycler();
    }

    @Override
    public void ThucHienXoaView() {
        viewXuLyTrangChu = null;
        disposableObserverAndroid.dispose();
        disposableObserverUser.dispose();
    }

    @Override
    public void ThucHienClearData() {



    }


}
