package com.example.jonathan.seisme_miniprojet;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private UiSettings mUiSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mUiSettings = mMap.getUiSettings();

        mUiSettings.setZoomControlsEnabled(true);
        pinMarkers();

    }

    public void pinMarkers() {

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        for (int i = 0 ; i < ( extras.size() / 2 ) ; i++) {

            String latStr = extras.getString(i + "-lat");
            String longitudeStr = extras.getString(i + "-long");

            // Message générique si erreur lors de la récupération du titre du séisme
            String title = "Position du séisme";
            String mag = "";
            //String desc = "";
            title = extras.getString(i + "-title");
            mag = extras.getString(i + "-mag");
            //desc = extras.getString(i + "-description");


            if (latStr != null && longitudeStr != null) {
                double lat = Double.parseDouble(latStr);
                double longitude = Double.parseDouble(longitudeStr);
                LatLng quake =  new LatLng(lat, longitude);
                mMap.addMarker(new MarkerOptions()
                        .position(quake)
                        .title(title)
                        .snippet("Magnitude : " + mag));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(quake));
            }

            //System.out.println(lat + " " + longitude);
        }

        System.out.println(extras);
    }
}
