package com.example.demogg.Download;

import android.location.Location;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseLatLng {

    String data;

    public ParseLatLng(String data) {
        this.data = data;
    }


    public Location getLatLng() {
        Location location = null;
        try {
            JSONObject jsonObject = new JSONObject(data);
            //JSONObject jsonObject1 = jsonObject.getJSONObject("candidates");
           JSONArray resultArray = jsonObject.getJSONArray("candidates");

            for (int i = 0; i < resultArray.length();i++){
                JSONObject objectResult = resultArray.getJSONObject(i);
                JSONObject objectGeometry = objectResult.getJSONObject("geometry");
                JSONObject objectLocation = objectGeometry.getJSONObject("location");
                Double lat = objectLocation.getDouble("lat");
                Double lng = objectLocation.getDouble("lng");
                location = new Location("");
                location.setLatitude(lat);
                location.setLongitude(lng);
                Log.d("AAA1",location.toString());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return location;
    }
}
