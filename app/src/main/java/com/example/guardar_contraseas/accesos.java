package com.example.guardar_contraseas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class accesos {

    // AGREGAMOS LA CLASE DEL HELPER

    private SQLiteDatabase db;
    private DataBaseHelper dbhelper;

    public accesos(Context context){

        dbhelper = new DataBaseHelper(context);
        db = dbhelper.getWritableDatabase();
    }

    //EMPIZA LA GENERACION DEL CRUD

    public long NewPass(String usuario, String proveedor, String password){

        ContentValues contenido = new ContentValues();
        contenido.put(DataBaseHelper.USUARIO, usuario);
        contenido.put(DataBaseHelper.PROVEEDOR, proveedor);
        contenido.put(DataBaseHelper.PASSWORD, password);

        return db.insert(DataBaseHelper.NOMBRE_TABLA,null,contenido);
    }

    //LISTAR LAS CONTRASEÑAS GUARDADAS
    public List<String> obtenerPassword(){
        List<String> lista = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DataBaseHelper.NOMBRE_TABLA, null);

        while(cursor.moveToNext()){
            String medico = cursor.getInt(0) + " - " +
                    cursor.getString(1) + " - " +
                    cursor.getString(2) +  " - " +
                    cursor.getString(3);
            lista.add(medico);
        }
        cursor.close();
        return lista;
    }

    //ACCION PARA ACTUALIZAR LAS CONTARSEÑAS Y DEMAS DATOS
    public int editarDatos(int id, String usuario, String proveedor, String password){
        ContentValues contenido = new ContentValues();
        contenido.put(DataBaseHelper.USUARIO,usuario);
        contenido.put(DataBaseHelper.PROVEEDOR,proveedor);
        contenido.put(DataBaseHelper.PASSWORD,password);

        return db.update(DataBaseHelper.NOMBRE_TABLA,contenido,"id_accesos=?", new String[]{String.valueOf(id)});
    }

    //ACCION PARA ELIMINAR REGISTROS
    public int eliminarDatos(int id){
        return db.delete(DataBaseHelper.NOMBRE_TABLA,"id_accesos=?", new String[]{String.valueOf(id)});
    }
}
