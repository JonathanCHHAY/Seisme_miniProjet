package com.example.jonathan.seisme_miniprojet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        ListView lvEarthqJ = (ListView) findViewById(R.id.lvEarthq);

        //Création de la ArrayList qui nous permettra de remplire la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<>();

        try {
            //Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
            SimpleAdapter adapter = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.affichageitem,
                    new String[] {"img", "titre", "description"}, new int[] {R.id.img, R.id.titre, R.id.description});

            //On attribut à notre listView l'adapter que l'on vient de créer
            lvEarthqJ.setAdapter(adapter);

            //final TextView tvDateR = (TextView) findViewById(R.id.tvJson);
            new EarthqAsyncTask().execute(listItem, adapter);
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }





    }
}
