package com.example.demogg;

import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, android.location.LocationListener, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {

    GoogleMap mMap;
    SupportMapFragment mapFragment;
    MarkerOptions options;
    Marker currentMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        options = new MarkerOptions();
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onLocationChanged(Location location) {

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

        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        LatLng myLatLng =new LatLng(-34,151);

        if (myLatLng != null) {


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
            mMap = googleMap;
        }
    }
}
