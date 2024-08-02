package com.ebertcs.tarea_pmdm03.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.ebertcs.tarea_pmdm03.Modelo.Cliente;

import java.util.ArrayList;

public class DBCliente extends DBHelper {

    Context context;

    public DBCliente(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    //registra cliente
    public long insetarCLiente(String nombre, String telefono){

        long id=0;
        try{
            DBHelper dbHelper = new DBHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre" , nombre);
            values.put("telefono" , telefono);

            id = db.insert(TABLE_NAME,null,values);

        }catch (Exception e){
            e.toString();
        }

        return id;

    }

    //muetsra todos los clientes
    public ArrayList<Cliente> mostrarClientes(){

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Cliente> listaClientes = new ArrayList<>();
        Cliente cliente = null;
        Cursor cursorClientes=null;

        cursorClientes = db.rawQuery("SELECT * FROM "+TABLE_NAME + " ORDER BY id DESC" , null);

        if(cursorClientes.moveToFirst()){

            do {
                cliente = new Cliente();
                cliente.setId(cursorClientes.getInt(0));
                cliente.setNombre(cursorClientes.getString(1));
                cliente.setTelefono(cursorClientes.getString(2));
                listaClientes.add(cliente);
            }while (cursorClientes.moveToNext());
        }

        cursorClientes.close();

        return listaClientes;
    }

    //nformacion de un solo cliente
    public  Cliente verCliente(int id){

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cliente cliente = null;
        Cursor cursorClientes;

        cursorClientes = db.rawQuery("SELECT * FROM "+TABLE_NAME + " WHERE id = " + id + " LIMIT 1 ", null);

        if(cursorClientes.moveToFirst()){

         cliente = new Cliente();
         cliente.setId(cursorClientes.getInt(0));
         cliente.setNombre(cursorClientes.getString(1));
         cliente.setTelefono(cursorClientes.getString(2));

        }

        cursorClientes.close();
        return cliente;
    }

    //permite editar cliente
    public boolean editarCliente(int id, String nombre, String telefono) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_NAME + " SET nombre = '" + nombre + "', telefono = '" + telefono + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    //permite eliminar un cliente
    public boolean eliminarCliente(int id) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }



}
