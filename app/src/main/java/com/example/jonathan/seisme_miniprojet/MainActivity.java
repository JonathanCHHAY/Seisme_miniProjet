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
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<HashMap<String, String>> listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Récupération de la listview créée dans le fichier main.xml
        final ListView lvEarthqJ = (ListView) findViewById(R.id.lvEarthq);

        //Création de la ArrayList qui nous permettra de remplire la listView
        listItem = new ArrayList<>();

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

                    quakeDetailsIntent.putExtra("title", map.get("title"));
                    quakeDetailsIntent.putExtra("mag", map.get("mag"));
                    quakeDetailsIntent.putExtra("description", map.get("description"));
                    quakeDetailsIntent.putExtra("x0", map.get("x0"));
                    quakeDetailsIntent.putExtra("x1", map.get("x1"));
                    quakeDetailsIntent.putExtra("x2", map.get("x2"));
                    quakeDetailsIntent.putExtra("url", map.get("url"));

                    /*
                    System.out.println("["
                            +  map.get("x0") + ", "
                            +  map.get("x1") + ", "
                            +  map.get("x2") + "]");
                    //*/

                    startActivity(quakeDetailsIntent);


                }
            });
        }


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btMap:
                Intent mapIntent = new Intent(MainActivity.this, MapsActivity.class);

                // On charge les coordonnées des seismes
                for (int i = 0 ; i < listItem.size() ; i++) {
                    mapIntent.putExtra(i + "-long", listItem.get(i).get("x0"));
                    mapIntent.putExtra(i + "-lat", listItem.get(i).get("x1"));
                    mapIntent.putExtra(i + "-title", listItem.get(i).get("title"));
                    mapIntent.putExtra(i + "-mag", listItem.get(i).get("mag"));
                    mapIntent.putExtra(i + "-description", listItem.get(i).get("description"));
                }

                startActivity(mapIntent);
                break;
        }
    }
}


