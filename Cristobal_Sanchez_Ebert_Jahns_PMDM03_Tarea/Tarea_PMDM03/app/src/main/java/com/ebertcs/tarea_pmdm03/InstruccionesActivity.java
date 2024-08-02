package com.ebertcs.tarea_pmdm03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InstruccionesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrucciones);

        StringBuilder parrafo1= new StringBuilder("- La aplicación en la pantalla principal nos da la bienvenida" +
                " y muestra 4 items en una lista y estos son: Lista de clientes, Modificar mensaje publicitario, Enviar mensaje publicitario y Salir." +
                "Tambien tiene un menu en el toolbar con dos items: Creditos e Instrucciones");

        TextView linea1 = findViewById(R.id.textParrafo1);
        linea1.setText(parrafo1.toString());

        StringBuilder parrafo2= new StringBuilder("- En la vista de Lista de clientes, se muestran todos los clientes y sus números de teléfono que han" +
                " sido registrados en la base de datos, si es la primera vez que se usa la aplicación al entrar a lista de clientes  no aparece nada, " +
                "tenemos que registrar un nuevo cliente desde el menu que tenemos en el toolbar, en el menu tambien encontraremos un botón que dice volver" +
                "que nos lleva a la vista principal. Si queremos editar la información de un cliente, tenemos  " +
                "que presionar durante un tiempo prolongado sobre su posición en la lista y se nos abrira un alert que nos preguntara si queremos ver información del cliente o no. Si decimos " +
                "que si, nos abre una vista donde vemos la información del cliente y tambien dos botones, uno para eliminar el cliente seleccionado y otro botón flotante para" +
                "poder editar la información mostrada" );

        TextView linea2 = findViewById(R.id.textParrafo2);
        linea2.setText(parrafo2.toString());

        StringBuilder parrafo3= new StringBuilder("- Cuando seleccionamos Modificar mensaje publicitario por primera vez , encontraremos una caja de texto vacia a la cual le vamos  " +
                "a escribir el mensaje publicitario, este mensaje debe contener un * que es el que sera remplazado por el nombre del cliente, cuando presionamos en guardar el mensaje queda guardado pero si " +
                "modificamos información de la caja de texto y presionamos guardar nuevamente este mensaje se actualiza." );

        TextView linea3 = findViewById(R.id.textParrafo3);
        linea3.setText(parrafo3.toString());

        StringBuilder parrafo4= new StringBuilder("- Cuando seleccionamos enviar mensaje publicitario, nos muestra una lista con el mensaje publicitario, replicandolo para cada cliente. Es decir el " +
                "el * se reemplaza por los nombres de los clientes. Al presionar en enviar mensaje SMS se enviara un SMS a cada uno de los clientes de la lista.");


        TextView linea4 = findViewById(R.id.textParrafo4);
        linea4.setText(parrafo4.toString());

        StringBuilder parrafo5= new StringBuilder("- Cuando seleccionamos Salir se cierra la aplicación.");

        TextView linea5 = findViewById(R.id.textParrafo5);
        linea5.setText(parrafo5.toString());

        Button volver= findViewById(R.id.btnVolverMain);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InstruccionesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}