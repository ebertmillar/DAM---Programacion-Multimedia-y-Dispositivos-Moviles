package com.ebertcs.tarea_pmdm03.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "publicidad.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "clientes";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);

    }
    //crea la tabla en la base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
     db.execSQL("CREATE TABLE " + TABLE_NAME + "( " +
             "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
             "nombre TEXT NOT NULL, " +
             "telefono TEXT NOT NULL) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
