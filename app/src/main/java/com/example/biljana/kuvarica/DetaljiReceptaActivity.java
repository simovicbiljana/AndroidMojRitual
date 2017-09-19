package com.example.biljana.kuvarica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class DetaljiReceptaActivity extends AppCompatActivity {
    private TextView textViewNaziv;
    private TextView textViewOpis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalji_recepta);
        textViewNaziv = (TextView) findViewById(R.id.tv_nazivJela);
        textViewOpis = (TextView) findViewById(R.id.tv_opisJela);
        Intent i = getIntent();
        String nazivJela = i.getStringExtra("nazivRecepta");
        String opisJela = i.getStringExtra("opisJela");

        textViewOpis.setText(opisJela);
        textViewNaziv.setText(nazivJela);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //naduvaj ovaj layout
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_sms) {
            //ovde ide logika za sms
        }
        else if (id == R.id.menu_mail) {
            //ovde ide logika za mejl
        }


        return super.onOptionsItemSelected(item);
    }
}
