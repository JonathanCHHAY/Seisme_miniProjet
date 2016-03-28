package com.example.jonathan.seisme_miniprojet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


// git : programFiles(x86)/SmartGitHS/git/bin/git.exe
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Récupération de la listview créée dans le fichier main.xml
        final ListView lvEarthqJ = (ListView) findViewById(R.id.lvEarthq);

        //Création de la ArrayList qui nous permettra de remplire la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<>();

        //Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
        SimpleAdapter adapter = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.affichageitem,
                new String[] {"mag", "title", "description"}, new int[] {R.id.mag, R.id.titre, R.id.description});

        //On attribut à notre listView l'adapter que l'on vient de créer
        if (lvEarthqJ != null) {
            lvEarthqJ.setAdapter(adapter);
        }

        //final TextView tvDateR = (TextView) findViewById(R.id.tvJson);
        new QuakeAsyncTask().execute(listItem, adapter);

        if (lvEarthqJ != null) {
            lvEarthqJ.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //on récupère la HashMap contenant les infos de notre item (titre, description, img)
                    HashMap<String, String> map = (HashMap<String, String>) lvEarthqJ.getItemAtPosition(position);
                    Intent quakeDetailsIntent = new Intent(MainActivity.this, QuakeDetailsActivity.class);
                    //i.putExtra("msgToB", etMsg.getText().toString());
                    startActivity(quakeDetailsIntent);


                }
            });
        }
    }


}


