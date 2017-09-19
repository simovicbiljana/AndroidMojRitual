package com.example.biljana.kuvarica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class DodajReceptActivity extends AppCompatActivity {
    private EditText editTextNaziv;
    private EditText editTextOpis;
    private Button btnDodaj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_recept);

        editTextNaziv = (EditText)findViewById(R.id.naziv_recepta);
        editTextOpis = (EditText) findViewById(R.id.opis_jela);
        btnDodaj = (Button) findViewById(R.id.btn_dodaj);


        btnDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nazivJela = editTextNaziv.getText().toString();
                String opisJela = editTextOpis.getText().toString();


                File file = getFileStreamPath("recepti.txt");
                if (file.exists()) {
                    try {
                        FileOutputStream writer = openFileOutput(file.getName(), MODE_APPEND);
                        String recept = nazivJela + "," + opisJela + "\n";
                        writer.write(recept.getBytes());
                        writer.flush();
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        file.createNewFile();
                        FileOutputStream writer = openFileOutput(file.getName(), MODE_APPEND);
                        String recept = nazivJela + "," + opisJela + "\n";
                        writer.write(recept.getBytes());
                        writer.flush();
                        writer.close();
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
