package com.example.jonathan.seisme_miniprojet;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Seisme extends AsyncTask<Object, Integer, String>{

    TextView tvJson;
    //ArrayList <String> earthquakes = null;
    String earthquakes = "";

    @Override
    protected String doInBackground(Object... params) {

        tvJson = (TextView) params[0];
        String etJson = "";

        try {

            URL url = new URL("http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/4.5_day.geojson");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader(
                new InputStreamReader(urlConnection.getInputStream() ) );
                String data = "";

                while ((data = in.readLine()) != null) {
                    etJson += data;
                }

                System.out.println(etJson);


                //String inJson = "";
                JSONObject reader = new JSONObject(etJson);
                JSONArray features  = reader.getJSONArray("features");

                for (int i = 0 ; i < features.length() ; i++) {
                    JSONObject feature = features.getJSONObject(i);
                    JSONObject properties = feature.getJSONObject("properties");
                    String place = properties.getString("place");
                    //JSONObject place = ;
                    System.out.println(place + "\n");
                }

                /*
                JSONObject  jsonRootObject = new JSONObject(etJson);

                //Get the instance of JSONArray that contains JSONObjects
                JSONArray jsonArray = jsonRootObject.optJSONArray("features");

                //Iterate the jsonArray and print the info of JSONObjects
                for(int i=0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String name = jsonObject.optString("place");

                    //earthquakes.add("lieu= " + name) ;
                    earthquakes += "lieu= " + name + "\n";
                    System.out.println(earthquakes);
                }
                */

                in.close(); // et on ferme le flux

                //return ( earthquakes );
                return ( etJson );
            }
        } catch (Exception e) {
            System.out.println("Error fetch earthquakes");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        tvJson.setText(s);
    }
}
