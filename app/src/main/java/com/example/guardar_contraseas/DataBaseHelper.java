package com.example.guardar_contraseas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "gestor_password.db"; //nombre de la base de datos
    private static final int DATABASE_VERSION = 1; //PRIMERA VERSION DE LA BD

    //DEFINIMOS LAS COLUMNAS Y EL NOMBRE DE LA BD

    public static final String NOMBRE_TABLA = "accesos";
    public static final String ID_ACCESOS = "id_accesos";
    public static final String USUARIO = "name_user";
    public static final String PROVEEDOR = "proveedor";
    public static final String PASSWORD = "password";

    //CONSULTA PARA CREAR LA BD Y LA TABLA CON SUS COLUMNAS

    public static final String CREATE_TABLE_ACCESOS =
            "CREATE TABLE " + NOMBRE_TABLA + " (" +
                    ID_ACCESOS + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    USUARIO + " TEXT, " +
                    PROVEEDOR + " TEXT, " +
                    PASSWORD + " TEXT);";

    //CREAR LA BD

    public DataBaseHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){ db.execSQL(CREATE_TABLE_ACCESOS);} //CREA LA TABLA ACCESOS

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion){
        db.execSQL("DROP TABLE IF EXISTS " + NOMBRE_TABLA);
        onCreate(db);
    }

}
