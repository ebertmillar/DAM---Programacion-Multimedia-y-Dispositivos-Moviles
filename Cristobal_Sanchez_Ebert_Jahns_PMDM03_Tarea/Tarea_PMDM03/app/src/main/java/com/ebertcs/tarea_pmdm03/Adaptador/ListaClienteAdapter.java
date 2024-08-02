package com.ebertcs.tarea_pmdm03.Adaptador;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.ActionMode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.ebertcs.tarea_pmdm03.InfoClienteActivity;
import com.ebertcs.tarea_pmdm03.Modelo.Cliente;
import com.ebertcs.tarea_pmdm03.R;

import java.util.ArrayList;

public class ListaClienteAdapter extends RecyclerView.Adapter<ListaClienteAdapter.ClienteViewHolder> {

    private ArrayList<Cliente> listaClientes;

    private Context context;
    private int selectedPosition = RecyclerView.NO_POSITION;


    public ListaClienteAdapter(ArrayList<Cliente> listaClientes){
        this.listaClientes = listaClientes;

    };
    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cliente,null,false);
        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {
        holder.viewNombre.setText(listaClientes.get(position).getNombre());
        holder.viewTelefono.setText(listaClientes.get(position).getTelefono());
    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    public class ClienteViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre,viewTelefono;

        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewTelefono = itemView.findViewById(R.id.viewTelefono);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    context = v.getContext();

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Ver Información del Cliente");
                    builder.setMessage("¿Quieres ver la información del cliente?");
                    builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Lógica para mostrar la información del cliente
                            Intent intent = new Intent(context, InfoClienteActivity.class);
                            intent.putExtra("ID", listaClientes.get(getAdapterPosition()).getId());
                            context.startActivity(intent);
                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Lógica cuando se selecciona "No"
                            dialog.dismiss();
                        }
                    });

                    // Mostrar el AlertDialog
                    builder.create().show();

                    return true;
                }
            });}
    }

}
