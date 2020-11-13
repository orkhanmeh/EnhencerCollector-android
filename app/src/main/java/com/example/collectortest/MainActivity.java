package com.example.collectortest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getVisitorID();
        //setVisitorID("firstTest");
        /*try {
            this.listingPage("deneme1", "deneme2", "deneme3");
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("logo", "none");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d("logo", "done");
                Log.d("logo", response.toString());
            }
        });
        return "";

    }

    public void listingPage(String productCategory1, String productCategory2, String productCategory3) throws IOException {
        String city = "Ankara";
        String country = "Turkey";
        String deviceType = "Android App";

        JSONObject paramsObj = new JSONObject();
        try {
            paramsObj.put("customerID", "denemeCustomerID");
            paramsObj.put("visitorID", "denemeVisitor");
            paramsObj.put("productCategory1", productCategory1);
            paramsObj.put("productCategory2", productCategory2);
            paramsObj.put("productCategory3", productCategory3);
            paramsObj.put("city", "Santa");
            paramsObj.put("country", "Laplandia");
            paramsObj.put("deviceType", "Android App");
            paramsObj.put("token", "undefined");


        } catch (JSONException e) {
            Log.e("err", e.getMessage());
        }

        String params = paramsObj.toString();

        post("https://app.enhencer.com/api/newListingEvent/", params);
    }

    public void productPage(String productID, int price, String productCategory1, String productCategory2, String productCategory3) throws IOException {
        String city = "Ankara";
        String country = "Turkey";
        String deviceType = "Android App";

        JSONObject paramsObj = new JSONObject();
        try {
            paramsObj.put("customerID", "denemeCustomerID");
            paramsObj.put("visitorID", "denemeVisitor");
            paramsObj.put("productCategory1", "pr1");
            paramsObj.put("productCategory2", "pr2");
            paramsObj.put("productCategory3", "pr3");
            paramsObj.put("city", "Santa");
            paramsObj.put("country", "Laplandia");
            paramsObj.put("deviceType", "Android App");
            paramsObj.put("token", "undefined");


        } catch (JSONException e) {
            Log.e("err", e.getMessage());
        }

        String params = paramsObj.toString();

        Log.d("logo", "params");
        Log.d("logo", params);

        post("https://app.enhencer.com/api/newListingEvent/", params);
        //post("https://httpbin.org/anything", params);
    }

    public void getLocation() {

        LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100,
                    10, mLocationListener);
            return;
        }

    }

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            //your code here
            Log.d("logo", "loc changed");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d("logo", "stat changed");
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.d("logo", "prov enabled");
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.d("logo", "prov disabled");
        }
    };
}