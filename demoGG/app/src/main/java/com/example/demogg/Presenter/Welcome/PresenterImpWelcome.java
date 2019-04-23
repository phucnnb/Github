package com.example.demogg.Presenter.Welcome;

import android.content.SharedPreferences;
import android.location.Location;

import com.google.android.gms.common.api.GoogleApiClient;

public interface PresenterImpWelcome {
    void ThucHienCheckGPS(GoogleApiClient googleApiClient);
    void ThucHienChuyenActivity(Location location, SharedPreferences share);
    void ThucHienShowAlert();
}
