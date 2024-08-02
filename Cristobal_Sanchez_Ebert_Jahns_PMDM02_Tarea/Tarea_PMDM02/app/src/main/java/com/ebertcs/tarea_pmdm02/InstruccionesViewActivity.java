package com.ebertcs.tarea_pmdm02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InstruccionesViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrucciones_view);

        StringBuilder parrafo1= new StringBuilder("- La aplicación en la pantalla principal nos da la bienvenida" +
                " y muestra tres botones: Empezar, Instrucciones, Creditos y Terminar");

        TextView linea1 = findViewById(R.id.textParrafo1);
        linea1.setText(parrafo1.toString());

        StringBuilder parrafo2= new StringBuilder("- Hay que presionar el botón empezar para ir a la \nvista que" +
                " contiene la lista(ListView) con todos los continentes con los que vamos a interactuar." );

        TextView linea2 = findViewById(R.id.textParrafo2);
        linea2.setText(parrafo2.toString());

        StringBuilder parrafo3= new StringBuilder("- Al Presionar sobre un continente," +
                " se nos abre una nueva vista que contiene un breve resumen del continente seleccionado, " +
                "además tenemos 3 paises que pertenecen a este continente. Estos paises \npueden ser seleccionados a través del " +
                "componente CheckBox y dependiendo de cual seleccionemos al " +
                "presionar en el botón Ver paises nos mostrara el nombre y mapa del o los paises selccionados. " +
                "Si presionamos el botón Ver paises y no se ha seleccionado al menos un pais se mostrara " +
                "un AlertDialog que indica que hay que seleccionar al menos uno.  " );

        TextView linea3 = findViewById(R.id.textParrafo3);
        linea3.setText(parrafo3.toString());

        StringBuilder parrafo4= new StringBuilder("- Cada vista contiene un botón que sirve para volver hacia atrás");

        TextView linea4 = findViewById(R.id.textParrafo4);
        linea4.setText(parrafo4.toString());

        StringBuilder parrafo5= new StringBuilder("- El botón Terminar de la pantalla de bienvenida sirve para cerrar la aplicación");

        TextView linea5 = findViewById(R.id.textParrafo5);
        linea5.setText(parrafo5.toString());

        Button volver= findViewById(R.id.btnVolverMain);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InstruccionesViewActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}