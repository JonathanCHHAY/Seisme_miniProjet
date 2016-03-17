package com.example.jonathan.seisme_miniprojet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;


// git : programFiles(x86)/SmartGitHS/git/bin/git.exe
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvDateR = (TextView) findViewById(R.id.tvJson);
        new Seisme().execute(tvDateR);
    }
}
