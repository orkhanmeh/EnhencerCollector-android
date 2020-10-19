package com.example.collectortest;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listingPage("deneme1", "deneme2", "deneme3");

    }

    public void listingPage (String productCategory1, String productCategory2, String productCategory3) {
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
            paramsObj.put("token", "5f883e921523d77504eaea12");


        } catch(JSONException e) {
            Log.e("err", e.getMessage());
        }

        String params = paramsObj.toString();

        CallAPI callAPI = new CallAPI();
        callAPI.execute("https://app.enhencer.com/api/newListingEvent/", params);
    }
}