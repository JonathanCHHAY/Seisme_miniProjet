package com.example.jonathan.seisme_miniprojet;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        // get data via the key
        String x0 = extras.getString("x0");
        String x1 = extras.getString("x1");
        String x2 = extras.getString("x2");
        if (x0 != null && x1 != null && x2 != null) {
            TextView tvMsgSent = (TextView) findViewById(R.id.tvCoordGPS);
            String coordGPSTxt = x0 + ", " + x1 + ", " + x2;
            tvMsgSent.setText(coordGPSTxt);
        }

        /*
            // On affiche le message de A
            TextView tvMsgSent = (TextView) findViewById(R.id.tvMsgSent);
            tvMsgSent.setText(msgFromA);
            */

    }

}
