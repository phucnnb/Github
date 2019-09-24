package com.example.demogg.View.TimDuong;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demogg.Download.Download;
import com.example.demogg.Download.DownloadData;
import com.example.demogg.Download.ParseDirections;
import com.example.demogg.Download.ParseLatLng;
import com.example.demogg.Presenter.MainActivity.PresenterLogicMainActivity;
import com.example.demogg.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
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
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimDuong extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,Download, OnMapReadyCallback, LocationListener {

    @BindView(R.id.btnAddress)
    Button btnAddress;

    @BindView(R.id.btnSearch)
    Button btnSearch;
    private Location locationDiemDen,myLocation;
    private Double lat,lng;
    private GoogleApiClient googleApiClient;
    private SupportMapFragment mapFragment;
    private LocationRequest mLocationRequest;
    private Marker currentMarker,currentMarker1;
    private LatLng vitrihientai,vitriden;
    private ArrayList markerPoints= new ArrayList();
    private GoogleMap mMap;
    List<List<HashMap<String, String>>> routes = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_duong);
        ButterKnife.bind(this);
        myLocation = new Location("");
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
             lat = bundle.getDouble("LAT");
             lng = bundle.getDouble("LNG");
             vitrihientai = new LatLng(lat,lng);
             myLocation.setLatitude(lat);
             myLocation.setLongitude(lng);
             Log.d("AAA","hiện tại: " + lat + "------" + lng);
            Log.d("AAA","hiện tại: " + myLocation.toString());
        }

        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();

       mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        event();
    }

    private void event() {
        btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(TimDuong.this);
                LayoutInflater inflater = LayoutInflater.from(TimDuong.this);
                View view1 = inflater.inflate(R.layout.dialog_dia_chi,null);
                builder.setView(view1);

                Button btnXacNhan = view1.findViewById(R.id.btnXacNhan);
                final EditText editDiaChi = view1.findViewById(R.id.dialog_DiaChi);

                btnXacNhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String diaChi = editDiaChi.getText().toString();

                        String duongdan = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input="+diaChi+"&inputtype=textquery&fields=photos,formatted_address,name,rating,geometry&key=AIzaSyATP8TUa6esfUPC5pLfHyslVGufKcoa9V4";
                        String link = "https://geocoder.api.here.com/6.2/geocode.json?searchtext=so%209%20duong%20nguyen%20van%20bao%20quan%20go%20vap&app_id=UOIMI1QXlHft0KMCFGUE&app_code=t55nQfVgGWSn764usAeikQ&gen=8";
                        String replaceDuongDan = duongdan.replace(" ","%20");
                        DownloadData downloadData = new DownloadData(TimDuong.this,duongdan);
                        downloadData.execute();
                        try {
                            String data = downloadData.get();

                            Log.d("AAA",data);
                            ParseLatLng latLng = new ParseLatLng(data);
                            locationDiemDen = latLng.getLatLng();
                            vitriden = new LatLng(locationDiemDen.getLatitude(),locationDiemDen.getLongitude());
                            Log.d("AAA","điểm đến: " + locationDiemDen.toString());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(getApplicationContext(),"OK",Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatDirections();
            }
        });
    }

    @Override
    public Void Download(String data) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {
        mMap.clear();
        vitrihientai = new LatLng(location.getLatitude(),location.getLongitude());
        creatDirections();
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleApiClient.connect();
        mMap = googleMap;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient,mLocationRequest,this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    private void creatDirections() {
        markerPoints.add(vitrihientai);
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.icon);


        MarkerOptions options1 = new MarkerOptions();
        options1.icon(bitmapDescriptor);
        options1.position(vitrihientai);

        markerPoints.add(vitriden);


        BitmapDescriptor bitmapDescriptor1 = BitmapDescriptorFactory.fromResource(R.drawable.poiter_goods);
        MarkerOptions options2 = new MarkerOptions();
        options2.icon(bitmapDescriptor1);

        options2.position(vitriden);


        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(vitrihientai, 13));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(vitrihientai)
                .zoom(15)
                .bearing(0)
                .tilt(30)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        mMap.addMarker(options1);
        mMap.addMarker(options2);

        if (markerPoints.size() >= 2) {

            LatLng origin = vitrihientai;
            LatLng dest = vitriden;

            String url = getDirectionsUrl(origin, dest);

            DownloadData downloadTask = new DownloadData(this,url);

            downloadTask.execute(url);
            String dulieu = null;
            try {
                dulieu = downloadTask.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            Log.d("LLL",dulieu);
            ParseDirections parseDirections = new ParseDirections(dulieu);
            routes = parseDirections.parse();
            Log.d("KKK",routes.toString());
            DanDuong();
        }
    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        String sensor = "sensor=false";
        String mode = "mode=driving";
        String APIkey = "key=AIzaSyDC3FABtgxDyYDPdReJ7Eu8JHtGZhM0-AY";
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + mode + "&" + APIkey;

        String output = "json";

        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

        return url;
    }

    private void DanDuong() {
        ArrayList points = null;
        PolylineOptions lineOptions = null;
        MarkerOptions markerOptions = new MarkerOptions();

        for (int i = 0; i < routes.size(); i++) {
            points = new ArrayList();
            lineOptions = new PolylineOptions();

            List<HashMap<String, String>> path = routes.get(i);

            for (int j = 0; j < path.size(); j++) {
                HashMap<String, String> point = path.get(j);

                double lat = Double.parseDouble(Objects.requireNonNull(point.get("lat")));
                double lng = Double.parseDouble(Objects.requireNonNull(point.get("lng")));
                LatLng position = new LatLng(lat, lng);

                points.add(position);
            }

            lineOptions.addAll(points);
            lineOptions.width(12);
            lineOptions.color(Color.BLUE);
            lineOptions.geodesic(true);

        }
        mMap.addPolyline(lineOptions);
    }


}
