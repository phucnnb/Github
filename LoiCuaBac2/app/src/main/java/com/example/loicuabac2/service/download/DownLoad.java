package com.example.loicuabac2.service.download;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Paul on 6/25/2018.
 */

public class DownLoad extends AsyncTask<String, Void, String> {

    String link;
    TextView txtNoiDung;

    public DownLoad(String link, TextView txtNoiDung) {
        this.link = link;
        this.txtNoiDung = txtNoiDung;
    }

    @Override
    protected String doInBackground(String... strings) {
        String data = "";

        try {
            URL url = new URL(this.link);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder stringBuilder = new StringBuilder();
            String line = "";

            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }

            data = stringBuilder.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        txtNoiDung.setText(s);
    }
}
