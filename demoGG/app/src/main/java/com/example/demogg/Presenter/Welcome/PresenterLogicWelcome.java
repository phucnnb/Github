package com.example.demogg.Presenter.Welcome;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

import com.example.demogg.View.Welcome.ViewWelcome;
import com.google.android.gms.common.api.GoogleApiClient;

public class PresenterLogicWelcome implements PresenterImpWelcome {

    private ViewWelcome viewWelcome;
    private Context context;

    public PresenterLogicWelcome(ViewWelcome viewWelcome, Context context) {
        this.viewWelcome = viewWelcome;
        this.context = context;
    }

    @Override
    public void ThucHienCheckGPS(GoogleApiClient googleApiClient) {

    }

    @Override
    public void ThucHienChuyenActivity(Location location, SharedPreferences share) {
        Double latitude = location.getLatitude();
        Double longitude = location.getLongitude();

        SharedPreferences.Editor editor = share.edit();
        editor.putString("Latitude", String.valueOf(latitude));
        editor.putString("Longitude",String.valueOf(longitude));
        editor.commit();
        viewWelcome.ChuyenActivity();
    }

    @Override
    public void ThucHienShowAlert() {
        viewWelcome.ShowAlert();
    }
}
