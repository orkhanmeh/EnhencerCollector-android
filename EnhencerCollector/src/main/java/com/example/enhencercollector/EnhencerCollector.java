package com.example.enhencercollector;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;

public class EnhencerCollector {

    private static volatile EnhencerCollector collector = new EnhencerCollector();

    public static EnhencerCollector getInstance(){
        return collector;
    }

    private String customerID;
    private String visitorID;
    private String token;
    private SharedPreferences prefs;
    private Context context;

    public void EnhencerCollector (String token, Context context){
        prefs = context.getSharedPreferences("test", 0);
        this.token = token;
        this.context = context;

        generateVisitorID();

        /*if (getVisitorID() == null) {
            Log.d("logo", "set it");
        } else {
            Log.d("logo", "get it");
            Log.d("logo", getVisitorID());
        }*/

    }

    private String getVisitorID(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String visitorID = prefs.getString("enh_visitor_id", null);
        return visitorID;
    }

    void setVisitorID (String value){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("enh_visitor_id", value);
        editor.commit();
    }

    void generateVisitorID (){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        System.out.println(">>>>>>>>>>>>");
        System.out.println(timestamp);
        System.out.println("<<<<<<<<<<<<<<");
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
