package com.ebertcs.tarea_pmdm02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnIniciar;
    Button btnCreditos;
    Button btnInstrucciones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIniciar = findViewById(R.id.btnIniciar);
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ContinentesActivity.class);
                startActivity(intent);

            }
        });

        btnCreditos = findViewById(R.id.btnCreditos);
        btnCreditos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, CreditosViewActivity.class);
                startActivity(intent);

            }
        });

        btnInstrucciones = findViewById(R.id.btnInstrucciones);
        btnInstrucciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,InstruccionesViewActivity.class);
                startActivity(intent);

            }
        });

        Button cerrarButton = findViewById(R.id.terminar);

        cerrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cerrar la aplicación
                finish();
            }
        });


    }
}