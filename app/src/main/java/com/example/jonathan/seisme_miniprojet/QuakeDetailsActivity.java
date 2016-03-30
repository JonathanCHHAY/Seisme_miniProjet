package com.example.jonathan.seisme_miniprojet;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class QuakeDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quake_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Ceci est un FAB !", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }

        // Récupération des infos sur le titre du séisme, magnitude...
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        String title = extras.getString("title");
        String mag = extras.getString("mag");
        String timeDate = extras.getString("description");
        String x0 = extras.getString("x0");
        String x1 = extras.getString("x1");
        String x2 = extras.getString("x2");
        String url = extras.getString("url");

        // Affichage des infos sur le titre du séisme, magnitude...
        if (title != null) {
            TextView tvTitleQuakeR = (TextView) findViewById(R.id.tvTitleQuake);
            if (tvTitleQuakeR != null) {
                tvTitleQuakeR.setText(title);
            }
        }

        if (mag != null) {
            TextView tvMagR = (TextView) findViewById(R.id.tvMag);
            if (tvMagR != null) {
                tvMagR.setText(mag);
            }
        }

        if (timeDate != null) {
            TextView tvDateTimeR = (TextView) findViewById(R.id.tvDateTime);
            if (tvDateTimeR != null) {
                tvDateTimeR.setText(timeDate);
            }
        }

        if (x0 != null && x1 != null) {
            TextView tvCoordGpsR = (TextView) findViewById(R.id.tvCoordGps);
            String coordGPSTxt = "long : " +  x0 + "°, lat : " + x1 + "°";
            if (tvCoordGpsR != null) {
                tvCoordGpsR.setText(coordGPSTxt);
            }
        }

        if (x2 != null) {
            TextView tvCoordDepthR = (TextView) findViewById(R.id.tvDepth);
            if (tvCoordDepthR != null) {
                String lat = x2 + " km";
                tvCoordDepthR.setText(lat);
            }
        }

        // Affichage de la carte
        if (url != null) {
            System.out.println(url);

            WebView wvMapQuakeR = (WebView) findViewById(R.id.wvMapQuake);

            if (wvMapQuakeR != null) {
                wvMapQuakeR.loadUrl(url);
                wvMapQuakeR.setWebViewClient(new WebViewClient());
                wvMapQuakeR.getSettings().setJavaScriptEnabled(true);

            }
        }


    }

}
