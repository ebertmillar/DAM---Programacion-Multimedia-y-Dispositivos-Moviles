package com.ebertcs.tarea_pmdm03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> mensajero;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //añade toolbar a la vista
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //añade titulo al toolbar
        TextView titulo = (TextView) findViewById(R.id.txtTitleMain);
        titulo.setText("PDMD03");
        //lista donde van los item del desarollo de cada tarea
        ListView lista = (ListView) findViewById(R.id.lvMain);

        mensajero = new ArrayList<>();
        mensajero.add("Lista de clientes");
        mensajero.add("Modificar mensaje publicitario");
        mensajero.add("Enviar mensaje publicitario");
        mensajero.add("Salir");

        //pasa los datos del array a la lista
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_items_main, mensajero);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {//al presionar sobre un item ejecuta una accion
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        intent = new Intent(MainActivity.this, ListaClientesActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, EditarMensajeActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, MensajeActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        finishAffinity();
                        System.exit(0);
                }
            }
        });
    }

    //carga un menu en el toolbar donde tenemos item credito e instrucciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //funcionalidad para que cuando presiones un item del menu ejecute una accion
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.creditos) {
            Intent intent = new Intent(MainActivity.this, CreditosActivity.class);
            startActivity(intent);
        }

        if (item.getItemId() == R.id.instrucciones) {
            Intent intent = new Intent(MainActivity.this, InstruccionesActivity.class);
            startActivity(intent);
            finish();
        }
        return  true;
    }
}