package com.example.jonathan.seisme_miniprojet;

import android.os.AsyncTask;
import android.widget.SimpleAdapter;

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

public class QuakeAsyncTask extends AsyncTask<Object, Integer, String> {

    //Création de la ArrayList qui nous permettra de remplir la listView
    ArrayList<HashMap<String, String>> listItem;
    SimpleAdapter adapter;
    ArrayList<Quake> quakes;

    @Override
    protected String doInBackground(Object... params) {

        listItem = (ArrayList<HashMap<String, String>>) params[0];
        adapter = (SimpleAdapter) params[1];

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

                fetchEarthq(jsonData);
                updateListItem();

                in.close(); // et on ferme le flux

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

                String type = properties.getString("type");
                double mag = properties.getDouble("mag");
                String place = properties.getString("place");

                double time = properties.getDouble("time");
                String date = getDate((long) time, "dd/MM/yyyy hh:mm:ss");

                JSONObject geometry = feature.getJSONObject("geometry");
                JSONArray coordinates = geometry.getJSONArray("coordinates");

                // On caste la compsante récupérer en objet dans le bon type (int ou double)
                // sinon créer des ClassCastException qui font planter le programme
                double[] coords = new double[coordinates.length()];
                for ( int j = 0 ; j < coordinates.length() ; j++ ) {
                    if( coordinates.get(j).getClass().getSimpleName().equals("Double")) {
                        coords[j] = (double) coordinates.get(j);

                    } else {
                        coords[j] = (int) coordinates.get(j);
                    }
                }

                System.out.println(coordinates);
                System.out.println( coords[0] + " " +  coords[1] + " " +  coords[2]);

                Quake quake = new Quake(
                        mag,
                        type,
                        place,
                        date,
                        coords[0],
                        coords[1],
                        coords[2]
                );

                quakes.add(quake);
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

            Quake quake = quakes.get(i);

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

