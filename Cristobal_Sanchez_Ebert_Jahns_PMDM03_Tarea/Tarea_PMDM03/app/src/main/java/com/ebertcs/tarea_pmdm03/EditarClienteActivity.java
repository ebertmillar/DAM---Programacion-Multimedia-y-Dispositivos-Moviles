package com.ebertcs.tarea_pmdm03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ebertcs.tarea_pmdm03.Database.DBCliente;
import com.ebertcs.tarea_pmdm03.Modelo.Cliente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarClienteActivity extends AppCompatActivity {

    private Cliente cliente;
    private int id = 0;
    boolean correcto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cliente);

        TextView tituloToolbar = findViewById(R.id.txtTitleRegistrar);
        tituloToolbar.setText("EDITAR CLIENTE");

        EditText txtNombre = findViewById(R.id.txtNombre);
        EditText txtTelefono = findViewById(R.id.txtTelefono);

        Button btnActualizar = findViewById(R.id.bntRegistrar);
        btnActualizar.setText("Actualizar");

        FloatingActionButton btnVolver = findViewById(R.id.btnVolverListaClientesReg);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DBCliente dbCliente = new DBCliente(EditarClienteActivity.this);
        cliente = dbCliente.verCliente(id);

        if(cliente != null){
            txtNombre.setText(cliente.getNombre());
            txtTelefono.setText(cliente.getTelefono());
        }

        btnActualizar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String nombre = txtNombre.getText().toString();
                String telefono =txtTelefono.getText().toString();

                if (!nombre.toString().equals("") && !telefono.toString().equals("") && telefono.length() ==9) {
                    correcto = dbCliente.editarCliente(id, txtNombre.getText().toString(), txtTelefono.getText().toString());

                    if(correcto){
                        Toast.makeText(EditarClienteActivity.this, "Se ha Modificado los datos del cliente", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarClienteActivity.this, "Error al modificar datos del cliente", Toast.LENGTH_LONG).show();
                    }
                } else {
                    if(nombre.equals("")){
                        txtNombre.setError("Ingresa un Nombre");
                    }
                    if(telefono.length()!=9){
                        txtTelefono.setError("El numero debe tener 9 digitos");
                    }
                    if(telefono.equals("")){
                        txtTelefono.setError("Ingresa un Numero de Telefono");
                    }
                }
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               verRegistro();
            }
        });

    }

    private void verRegistro(){
        Intent intent = new Intent(this, InfoClienteActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}