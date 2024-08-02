package com.ebertcs.tarea_pmdm02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContinentesActivity extends AppCompatActivity {

    private ListView lista;
    private TextView items;
    private ArrayList<String> continentes;

    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continentes);

        items = (TextView)findViewById(R.id.tv_item_continentes);
        lista = (ListView) findViewById(R.id.lv1);

        continentes = new ArrayList<>();
        continentes.add("África");
        continentes.add("América");
        continentes.add("Asia");
        continentes.add("Europa");
        continentes.add("Oceanía");


        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item_continentes,continentes);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position){

                    case 0 :
                        intent = new Intent(ContinentesActivity.this, AfricaViewActivity.class);
                        startActivity(intent);
                        break;
                    case 1 :
                        intent = new Intent(ContinentesActivity.this, AmericaViewActivity.class);
                        startActivity(intent);
                        break;
                    case 2 :
                        intent = new Intent(ContinentesActivity.this, AsiaViewActivity.class);
                        startActivity(intent);
                        break;
                    case 3 :
                        intent = new Intent(ContinentesActivity.this, EuropaViewActivity.class);
                        startActivity(intent);
                        break;
                    case 4 :
                        intent = new Intent(ContinentesActivity.this, OceaniaViewActivity.class);
                        startActivity(intent);
                        break;

                }

            }
        });

        Button volver= findViewById(R.id.btnVolverMain);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContinentesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}