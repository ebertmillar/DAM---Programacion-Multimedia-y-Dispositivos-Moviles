package com.ebertcs.tarea_pmdm03;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ebertcs.tarea_pmdm03.Database.DBCliente;
import com.ebertcs.tarea_pmdm03.Modelo.Cliente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class InfoClienteActivity extends AppCompatActivity {


    private Cliente cliente;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_cliente);

        TextView txtNombre = findViewById(R.id.infoNombre);
        TextView txtTelefono = findViewById(R.id.infoTelefono);
        FloatingActionButton fabEditar= findViewById(R.id.fbEditar);
        FloatingActionButton fabEliminar= findViewById(R.id.fbEliminar);
        FloatingActionButton btnVolver = findViewById(R.id.btnVolverListaClientesInfo);


        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DBCliente dbCliente = new DBCliente(InfoClienteActivity.this);
        cliente = dbCliente.verCliente(id);

        if(cliente != null){
            txtNombre.setText(cliente.getNombre());
            txtTelefono.setText(cliente.getTelefono());
        }

        //permite editar el cliente
        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoClienteActivity.this, EditarClienteActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        //eliminara al cliente de la lista y de la base de datos
        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InfoClienteActivity.this);
                builder.setMessage("¿Desea eliminar este Cliente?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(dbCliente.eliminarCliente(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoClienteActivity.this, ListaClientesActivity.class);
                startActivity(intent);
            }
        });

    }
    private void lista(){
        Intent intent = new Intent(this, ListaClientesActivity.class);
        startActivity(intent);
    }
}