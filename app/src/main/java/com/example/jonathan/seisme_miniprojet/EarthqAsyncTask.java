package com.example.jonathan.seisme_miniprojet;

import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class EarthqAsyncTask extends AsyncTask<Object, Integer, String>{

    ArrayList<HashMap<String, String>> listItem;
    SimpleAdapter adapter;
    ArrayList <String> titles;
    ArrayList<Earthq> quakes;
    //String earthquakes = "";

    @Override
    protected String doInBackground(Object... params) {

        listItem = (ArrayList<HashMap<String, String>>) params[0];
        adapter = (SimpleAdapter) params[1];
        //titles = new ArrayList<>();
        //Création de la ArrayList qui nous permettra de remplir la listView

        //On déclare la HashMap qui contiendra les informations pour un item
        HashMap<String, String> map;
        ArrayList<Earthq> quakes;

        String jsonData = "";

        try {

            URL url = new URL("http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/4.5_day.geojson");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){

                // On récupère le JSON
                BufferedReader in = new BufferedReader(
                new InputStreamReader(urlConnection.getInputStream() ) );

                String jsonFlux;
                while ((jsonFlux = in.readLine()) != null) {
                    jsonData += jsonFlux;
                }


                //On rempli l'array liste des titres de séismes
                fetchEarthq(jsonData);
                updateListItem();

                /*
                // On rempli la map de la liste
                for(int i = 0 ; i < titles.size() ; i++) {
                    map = new HashMap<>();
                    map.put("titre", titles.get(i));
                    map.put("description", "");
                    map.put("img", String.valueOf(R.mipmap.ic_launcher));
                    listItem.add(map);
                }
                */

                //System.out.println(listItem);

                in.close(); // et on ferme le flux

                //return ( earthquakes );
                return ("ok");
            }
        } catch (IOException e) {
            System.out.println("Error fetch earthquakes");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        adapter.notifyDataSetChanged();
    }

    public boolean fetchEarthq(String jsonDatas) {

        titles = new ArrayList<>();

        try {

            JSONObject reader = new JSONObject(jsonDatas);
            JSONArray features  = reader.getJSONArray("features");

            for (int i = 0 ; i < features.length() ; i++) {
                JSONObject feature = features.getJSONObject(i);
                JSONObject properties = feature.getJSONObject("properties");
                //JSONObject geometry = properties.getJSONObject("geometry");
                String title = properties.getString("title");
                titles.add(title);
                System.out.println(title);
                //System.out.println(titles);

                //String type = properties.getString("type");
                //JSONObject place = ;

                /*
                System.out.println( i + " : " + properties.getString("type")
                        + " " + properties.getDouble("mag")
                        + " " + properties.getString("place") + "\n");
                */

                /*
                Earthq quake = new Earthq(
                    properties.getString("type"),
                    properties.getDouble("mag"),
                    properties.getString("place")
                );
                */

            }

            return true;

        } catch (JSONException e) {
            System.out.println("err fetch jsonInfos");
            e.printStackTrace();
            e.getMessage();
        }

        return false;
    }

    public void updateListItem() {

        for (int i = 0 ; i < titles.size() ; i++ ) {
            HashMap<String, String> map = new HashMap<>() ;
            map.put("title", titles.get(i));
            map.put("description", titles.get(i));
            map.put("img", String.valueOf(R.mipmap.ic_launcher));
            listItem.add(map);
        }

    }
}

