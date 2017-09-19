package com.example.biljana.kuvarica;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private FloatingActionButton fab;
    private ArrayList<Recept> receptiArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        receptiArray = new ArrayList<>();
        listView = (ListView) findViewById(R.id.list_view);
        fab = (FloatingActionButton) findViewById(R.id.fab_add);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DodajReceptActivity.class);
                startActivity(i);
            }
        });


        try {
            String recepti = "";
            File file = getFileStreamPath("recepti.txt");
            if (file.exists()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    recepti = recepti + line;
                }
                bufferedReader.close();

                int brojRecepata = recepti.split("\n").length;

                for (int i = 0; i < brojRecepata - 1; i++) {
                    String recept = recepti.split("\n")[0];
                    String nazivRecepta = recepti.split(",")[0];
                    String opisJela = recept.split(",")[1];
                    Recept recept1 = new Recept();
                    recept1.setNazivRecepta(nazivRecepta);
                    recept1.setOpisJela(opisJela);
                    receptiArray.add(recept1);
                }
                Log.d("Info", receptiArray.toString());
            } else {
                Toast.makeText(this, "Ne postoje recepti u textualnom fajlu", Toast.LENGTH_SHORT).show();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> naziviRecepta = new ArrayList<>();
        for (int i = 0;i < receptiArray.size(); i++) {
            naziviRecepta.add(receptiArray.get(i).getNazivRecepta());
        }

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_2, naziviRecepta);
        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DetaljiReceptaActivity.class);
                intent.putExtra("nazivRecepta", receptiArray.get(i).getNazivRecepta());
                intent.putExtra("opisJela", receptiArray.get(i).getOpisJela());
                startActivity(intent);
            }
        });
    }
}
