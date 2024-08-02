package com.ebertcs.tarea_pmdm03;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

import com.ebertcs.tarea_pmdm03.Database.DBCliente;
import com.ebertcs.tarea_pmdm03.Modelo.Cliente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class EditarMensajeActivity extends AppCompatActivity {

    SharedPreferences preferences; //Declaramos un objeto
    String guardarMensaje;
    EditText editMensaje;

    private static final int PERMISSION_REQUEST_SMS = 1;

    private ArrayList<Cliente> listaArrayClientes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_mensaje);

        FloatingActionButton btnVolver = findViewById(R.id.btnVolverMainSMSEdit);
        Toolbar toolbar = findViewById(R.id.toolbarSMSEdit);
        setSupportActionBar(toolbar);
        TextView titulo = findViewById(R.id.txtToolbarSMSEdit);
        titulo.setText("EDITAR MENSAJE");


        preferences = getSharedPreferences("miSharedPreferences",MODE_PRIVATE);
        editMensaje = (EditText) findViewById(R.id.textArea_information);

        guardarMensaje = preferences.getString("textKey", "");
        editMensaje.setText(guardarMensaje);


        Button btnGuardar = (Button) findViewById(R.id.btnGuardarMensaje);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToSave = editMensaje.getText().toString();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("textKey", textToSave);
                editor.apply();
                Toast.makeText(EditarMensajeActivity.this,"Texto guardado correctamente",Toast.LENGTH_SHORT).show();
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditarMensajeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}