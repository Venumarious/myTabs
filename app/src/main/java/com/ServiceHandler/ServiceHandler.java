package com.ServiceHandler;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServiceHandler {

    static String response = null;

    public ServiceHandler() {

    }

    public String makeServiceCall(String url) {
        try {
            // Url
            URL urlString=new URL(url);
            // http client
            HttpURLConnection con = (HttpURLConnection) urlString.openConnection();
            // Buffered Reader to run the url
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line + "\n");
            }
            response = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error: " + e.toString());
            response = e.toString();
        }
        return response;
    }
}
