package com.example.collectortest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            listingPage("deneme1", "deneme2", "deneme3");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Log.d("logo", "here");
        Log.d("logo", request.toString());

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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void listingPage (String productCategory1, String productCategory2, String productCategory3) throws IOException {
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


        } catch(JSONException e) {
            Log.e("err", e.getMessage());
        }

        String params = paramsObj.toString();

        Log.d("logo", "params");
        Log.d("logo", params);

        post("https://app.enhencer.com/api/newListingEvent/", params);
        //post("https://httpbin.org/anything", params);
    }
}