package com.example.demogg.Presenter.MainActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.provider.Settings;
import android.support.v7.app.AlertDialog;

import com.example.demogg.R;
import com.example.demogg.View.MainActivity.ViewMainActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class PresenterLogicMainActivity implements PresenterImpMainActivity {

    private ViewMainActivity viewMainActivity;
    private Context context;
    private MarkerOptions options = new MarkerOptions();
    private Marker currentMarker;



    public PresenterLogicMainActivity(ViewMainActivity viewMainActivity, Context context) {
        this.viewMainActivity = viewMainActivity;
        this.context = context;
    }

    @Override
    public void ThucHienSetMap(GoogleMap googleMap, Double lat, Double lng) {
        googleMap.clear();
        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        LatLng myLatLng =new LatLng(lat,lng);

            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLatLng, 15));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(myLatLng)
                    .zoom(15)
                    .bearing(0)
                    .tilt(30)
                    .build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.cast_abc_scrubber_control_off_mtrl_alpha);
            options.icon(bitmapDescriptor);
            options.position(myLatLng);
            currentMarker = googleMap.addMarker(options);
            currentMarker.showInfoWindow();
            viewMainActivity.SetMap(googleMap);
    }

    @Override
    public void ThucHienShowAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("GPS");
        alertDialog.setMessage("GPS chưa bật. Bạn cần bật GPS.");

        alertDialog.setPositiveButton("bật GPS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("Hủy Bỏ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }
}
