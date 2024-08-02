package com.ebertcs.tarea_pmdm03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.Toast;

import com.ebertcs.tarea_pmdm03.Adaptador.ListaClienteAdapter;
import com.ebertcs.tarea_pmdm03.Database.DBCliente;
import com.ebertcs.tarea_pmdm03.Modelo.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ListaClientesActivity extends AppCompatActivity {
    private RecyclerView listaClientes;
    private ArrayList<Cliente> listaArrayClientes;
    private List<String> mensajesConcatenados;

    private Adapter adapter;
    private ActionMode actionMode;


    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);

        Toolbar toolbar = findViewById(R.id.toolbarLista);
        setSupportActionBar(toolbar);

        cargarLista();

    }

    public void cargarLista() {

        listaClientes = findViewById(R.id.listaClientes);
        listaClientes.setLayoutManager(new LinearLayoutManager(this));

        DBCliente dbCliente= new DBCliente(ListaClientesActivity.this);

        listaArrayClientes = new ArrayList<>();

        ListaClienteAdapter adapter = new ListaClienteAdapter(dbCliente.mostrarClientes());
        listaClientes.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lista_clientes, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.itemRegistrar) {
            Intent intent = new Intent(ListaClientesActivity.this, RegistrarClienteActivity.class);
            startActivity(intent);
        }

        if (item.getItemId() == R.id.itemVolverMain) {
            Intent intent = new Intent(ListaClientesActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        return  true;
    }


}