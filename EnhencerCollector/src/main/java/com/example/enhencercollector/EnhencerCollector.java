package com.example.enhencercollector;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class EnhencerCollector {
    private String customerID;
    private String visitorID;
    private String token;
    private SharedPreferences prefs;

    public void EnhencerCollector (String token, Context context){
        prefs = context.getSharedPreferences("test", 0);
        this.token = token;
    }

    public void listingPage (String productCategory1, String productCategory2, String productCategory3) throws IOException {
        String city = "Ankara";
        String country = "Turkey";
        String deviceType = "Android App";


        String data = "";
        OutputStream out = null;

        try {
            URL url = new URL("https://app.enhencer.com/api/newListingEvent/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            out = new BufferedOutputStream(connection.getOutputStream());

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write(data);
            writer.flush();
            writer.close();
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
