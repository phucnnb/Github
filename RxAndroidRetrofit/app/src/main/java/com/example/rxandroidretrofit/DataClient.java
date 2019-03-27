package com.example.rxandroidretrofit;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DataClient {
    //@GET("android/jsonarray/")
    @GET("android.php")
    Observable<List<Android>> register();
}
