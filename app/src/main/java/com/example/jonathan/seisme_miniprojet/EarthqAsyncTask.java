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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class EarthqAsyncTask extends AsyncTask<Object, Integer, String> {

    ArrayList<HashMap<String, String>> listItem;
    SimpleAdapter adapter;
    ArrayList<String> titles;
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
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                // On récupère le JSON
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(urlConnection.getInputStream()));

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

        //titles = new ArrayList<>();
        quakes = new ArrayList<>();

        try {

            // root
            JSONObject reader = new JSONObject(jsonDatas);

            // liste seisme
            JSONArray features = reader.getJSONArray("features");
            for (int i = 0; i < features.length(); i++) {

                //Propriete de chaque seisme
                JSONObject feature = features.getJSONObject(i);
                JSONObject properties = feature.getJSONObject("properties");
                //JSONObject geometry = properties.getJSONObject("geometry");

                // Accès aux champs
                /*
                String title = properties.getString("type");
                titles.add(title);
                System.out.println(title);
                */

                String type = properties.getString("type");
                double mag = properties.getDouble("mag");
                String place = properties.getString("place");

                double time = properties.getDouble("time");
                String date = getDate((long) time, "dd/MM/yyyy hh:mm:ss");

                Earthq quake = new Earthq(
                        mag,
                        place,
                        date,
                        type
                );

                quakes.add(quake);

                /*
                System.out.println(i + " : " + quakes.get(i).getType()
                        + " " + quakes.get(i).getMag()
                        + " " + quakes.get(i).getPlace() + "\n");
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


        for (int i = 0; i < quakes.size(); i++) {

            Earthq quake = quakes.get(i);

            HashMap<String, String> map = new HashMap<>();
            map.put("title", quake.getPlace());
            map.put("mag", Double.toString(quake.getMag()));
            map.put("description", quake.getDate());
            listItem.add(map);
        }

    }

    // https://stackoverflow.com/questions/7953725/how-to-convert-milliseconds-to-date-format-in-android
    public static String getDate(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
}

