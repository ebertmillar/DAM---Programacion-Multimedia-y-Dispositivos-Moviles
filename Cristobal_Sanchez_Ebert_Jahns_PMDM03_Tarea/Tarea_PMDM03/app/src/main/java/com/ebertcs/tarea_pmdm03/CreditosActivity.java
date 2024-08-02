package com.ebertcs.tarea_pmdm03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CreditosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditos);

        //Pasando Texto a los TextViews
        TextView copyrightTextView = findViewById(R.id.textCredito);
        copyrightTextView.setText("© 2023 - Ebert Jahns Cristobal Sanchez");
        TextView fp = findViewById(R.id.textFp);
        fp.setText("Desarrollo de Aplicaciones Multiplataforma\n");
        TextView instituto = findViewById(R.id.textInstituto);
        instituto.setText("IES Maria Moliner");
        TextView modulo = findViewById(R.id.textModulo);
        modulo.setText("Programación Multimedia y Dispositivos Móviles");

        //botón volver - nos regresa a la ventana principal
        Button volver= findViewById(R.id.btnVolverMain);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreditosActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}