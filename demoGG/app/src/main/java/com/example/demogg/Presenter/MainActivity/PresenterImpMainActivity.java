package com.example.demogg.Presenter.MainActivity;

import com.google.android.gms.maps.GoogleMap;

public interface PresenterImpMainActivity {
    void ThucHienSetMap(GoogleMap googleMap, Double lat, Double lng);
    void ThucHienShowAlert();
}
