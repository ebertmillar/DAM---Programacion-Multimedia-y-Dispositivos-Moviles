package com.ebertcs.tarea_pmdm03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

import com.ebertcs.tarea_pmdm03.Database.DBCliente;
import com.ebertcs.tarea_pmdm03.Modelo.Cliente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MensajeActivity extends AppCompatActivity {

    SharedPreferences preferences; //Declaramos un objeto
    String mensajeRecuperado;
    EditText editMensaje;
    private static final int PERMISSION_REQUEST_SMS = 1;
    private ArrayList<Cliente> listaArrayClientes;
    private List<String> mensajesConcatenados;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje);


        FloatingActionButton btnVolver = findViewById(R.id.btnVolverMainSMSEnviar);
        Toolbar toolbar = findViewById(R.id.toolbarSMSEnviar);
        setSupportActionBar(toolbar);
        TextView titulo = findViewById(R.id.txtToolbarSMSEnviar);
        titulo.setText("ENVIAR SMS");

        ListView lista = findViewById(R.id.lvListaSMS);

        //permite traer el mensaje que se escribio en la actividad EditarMensajeActivity
        preferences = getSharedPreferences("miSharedPreferences",MODE_PRIVATE);
        mensajeRecuperado = preferences.getString("textKey", "valor_por_defecto");

        DBCliente dbCliente= new DBCliente(MensajeActivity.this);
        listaArrayClientes = dbCliente.mostrarClientes();

        mensajesConcatenados = new ArrayList<>();

        for (Cliente cliente : listaArrayClientes) {

            String nombre = cliente.getNombre().toString();
            String m = this.mensajeRecuperado.replace("*", nombre);

            mensajesConcatenados.add(m);
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item_sms,mensajesConcatenados);
        lista.setAdapter(adapter);



        Button enviarSMS = (Button) findViewById(R.id.btnSMS);
        enviarSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarMensajeSMS();
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MensajeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void enviarMensajeSMS() {
        // Verificar si ya se tiene el permiso
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED) {
            // Si se tiene el permiso, enviar el SMS
            sendSms();
        } else {
            // Si no se tiene el permiso, solicitarlo
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    PERMISSION_REQUEST_SMS);
        }

    }

    private void sendSms(){

        SmsManager smsManager = SmsManager.getDefault();

        DBCliente dbCliente= new DBCliente(MensajeActivity.this);
        listaArrayClientes = dbCliente.mostrarClientes();

        for (Cliente cliente : listaArrayClientes) {

            String nombre = cliente.getNombre().toString();
            String telefono = cliente.getTelefono().toString();

            String mensajeSMS = this.mensajeRecuperado.replace("*", nombre);
            smsManager.sendTextMessage("+34"+telefono,null, mensajeSMS, null, null);
            Toast.makeText(this,"SE HA ENVIADO EL MENSAJE A : " + listaArrayClientes.size()+ " CLIENTES",Toast.LENGTH_SHORT).show();
        }

    }





}