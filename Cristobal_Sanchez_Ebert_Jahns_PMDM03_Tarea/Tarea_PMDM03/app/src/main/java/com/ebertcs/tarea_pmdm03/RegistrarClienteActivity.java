package com.ebertcs.tarea_pmdm03;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.ebertcs.tarea_pmdm03.Database.DBCliente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RegistrarClienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cliente);

        Button guardar;
        EditText txtNombre,txtTelefono;
        FloatingActionButton btnVolver;

        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        btnVolver = findViewById(R.id.btnVolverListaClientesReg);

        guardar = findViewById(R.id.bntRegistrar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = txtNombre.getText().toString();
                String telefono =txtTelefono.getText().toString();

          if (!nombre.toString().equals("") && !telefono.toString().equals("") && telefono.length() ==9) {

                    DBCliente clientes = new DBCliente(RegistrarClienteActivity.this);
                    long id = clientes.insetarCLiente(nombre,telefono);

                    AlertDialog.Builder builder = new AlertDialog.Builder(RegistrarClienteActivity.this);
                    if(id > 0){

                        builder.setMessage("EL CLIENTE " + txtNombre.getText().toString() + " SE HA REGISTRADO CORRECTAMENTE")
                                .setTitle("REGISTRO")
                                .setCancelable(false);


                        // Crear el cuadro de diálogo
                        final AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                        // Crear un Handler para gestionar el retraso y cerrar el cuadro de diálogo después de cierto tiempo
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                alertDialog.dismiss(); // Cierra el cuadro de diálogo después del retraso
                            }
                        }, 1000); // 3000 milisegundos (3 segundos) - puedes ajustar el tiempo según tus necesidades

                        limpiarCampos();
                        ocultarTeclado();

                    }else{
                        builder.setMessage("ERROR AL INTENTAR REGISTRAR AL CLIENTE")
                                .setCancelable(false)
                                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss(); // Cierra el cuadro de diálogo
                                    }
                                });

                        // Crear y mostrar el cuadro de diálogo
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }

                }else{

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

            //limpia los campos despues de registrar el cliente
            public void limpiarCampos() {

                txtNombre.setText("");
                txtTelefono.setText("");
            }

        });

        //Vuelve a la lista de clientes
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrarClienteActivity.this, ListaClientesActivity.class);
                startActivity(intent);
            }
        });



    }

    //sirve para ocultar el teclado despues de presionar el boton registrar
    private void ocultarTeclado() {
        // Obtener el servicio del teclado virtual
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        // Ocultar el teclado
        View view = this.getCurrentFocus();
        if (view != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


}