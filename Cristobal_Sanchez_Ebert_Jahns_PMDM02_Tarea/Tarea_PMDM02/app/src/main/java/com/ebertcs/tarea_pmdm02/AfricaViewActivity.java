package com.ebertcs.tarea_pmdm02;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class AfricaViewActivity extends AppCompatActivity {

    Button btnVolverContinentes;
    Button verPaisesAfrica;

    CheckBox ch1;
    CheckBox ch2;
    CheckBox ch3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_africa_view);

        btnVolverContinentes = findViewById(R.id.btnVolverContinentes);
        btnVolverContinentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AfricaViewActivity.this, ContinentesActivity.class);
                startActivity(intent);

            }
        });

        ch1 = (CheckBox) findViewById(R.id.cbTanzania);
        ch2 = (CheckBox) findViewById(R.id.cbKenya);
        ch3 = (CheckBox) findViewById(R.id.cbUganda);
        verPaisesAfrica = findViewById(R.id.btnVerPaisesAfrica);
        verPaisesAfrica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Obtener las selecciones del usuario
                boolean option1Seleccionada = ch1.isChecked();
                boolean option2Seleccionada = ch2.isChecked();
                boolean option3Seleccionada = ch3.isChecked();

                if(!option1Seleccionada && !option2Seleccionada && !option3Seleccionada ){

                    AlertDialog.Builder builder = new AlertDialog.Builder(AfricaViewActivity.this);

                    // Configurar el título y el mensaje del diálogo
                    builder.setTitle("IMPORTANTE")
                            .setMessage("Tienes que seleccionar al menos un país")
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Código que se ejecutará cuando se haga clic en el botón "Aceptar"
                                    dialog.dismiss(); // Cierra el diálogo
                                }
                            });

                    // Mostrar el diálogo
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }else{

                    // Pasar la información a la siguiente actividad
                    Intent intent = new Intent(AfricaViewActivity.this, AfricaContentActivity.class);
                    intent.putExtra("OPCION_1", option1Seleccionada);
                    intent.putExtra("OPCION_2", option2Seleccionada);
                    intent.putExtra("OPCION_3", option3Seleccionada);
                    startActivity(intent);

                }

            }
        });


    }
}