package com.example.demogg.View.MainActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.demogg.Presenter.MainActivity.PresenterLogicMainActivity;
import com.example.demogg.R;
import com.example.demogg.View.TimDuong.TimDuong;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LocationListener,GoogleApiClient.ConnectionCallbacks,OnMapReadyCallback, android.location.LocationListener, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener, ViewMainActivity {


    private SupportMapFragment mapFragment;
    private PresenterLogicMainActivity logicMainActivity;
    private GoogleMap mMap;
    private SharedPreferences share;
    private Double lat, lng;
    private LocationManager locationManager;
    private GoogleApiClient googleApiClient;
    private Location location;
    private LocationRequest mLocationRequest;
    GoogleMap googleMap;

    @BindView(R.id.btnTimDuong)
    Button btnTimDuong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        share = getSharedPreferences("sharePre", MODE_PRIVATE);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();

        logicMainActivity = new PresenterLogicMainActivity(this, getApplicationContext());
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        event();

    }

    private void event() {
        btnTimDuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TimDuong.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("AAA", "reload");
        logicMainActivity.ThucHienSetMap(googleMap, location.getLatitude(), location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleApiClient.connect();

    }

    @Override
    public void SetMap(GoogleMap googleMap) {
        mMap = googleMap;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        if(location == null){
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient,mLocationRequest,this);
            Log.d("AAA", "updateLocation");

        }else {
            Log.d("AAA", "not updateLocation");
            logicMainActivity.ThucHienSetMap(googleMap, location.getLatitude(), location.getLongitude());
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient,mLocationRequest,this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        location = null;
    }
}
