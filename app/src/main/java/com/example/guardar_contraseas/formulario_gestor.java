package com.example.guardar_contraseas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class formulario_gestor extends AppCompatActivity {

    EditText txt_usuario, txt_proveedor, txt_password;
    Button btn_guardar, btn_salir, btn_eliminar;
    accesos accesosjava;
    ImageView btn_back_list;
    int idseleccionado = -1; // ID de la contraseña, -1 indica nuevo registro

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formulario_gestor);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txt_usuario = findViewById(R.id.txt_Usuario);
        txt_proveedor = findViewById(R.id.txt_proveedor);
        txt_password = findViewById(R.id.txt_contraseña);
        btn_guardar = findViewById(R.id.btn_Guardar);
        btn_salir = findViewById(R.id.btn_salir);
        btn_eliminar = findViewById(R.id.btn_eliminar);
        btn_back_list = findViewById(R.id.btn_back_list);
        accesosjava = new accesos(this);

        // Verificar si hay datos pasados desde la lista
        Intent intent = getIntent();
        if (intent.hasExtra("id")) {
            idseleccionado = intent.getIntExtra("id", -1);
            txt_usuario.setText(intent.getStringExtra("usuario"));
            txt_proveedor.setText(intent.getStringExtra("proveedor"));
            txt_password.setText(intent.getStringExtra("password"));
        }

        // BOTÓN PARA GUARDAR O ACTUALIZAR DATOS
        btn_guardar.setOnClickListener(v -> {
            String usuario = txt_usuario.getText().toString().trim();
            String proveedor = txt_proveedor.getText().toString().trim();
            String password = txt_password.getText().toString().trim();

            if (usuario.isEmpty() || proveedor.isEmpty() || password.isEmpty()) {
                Toast.makeText(formulario_gestor.this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                return;
            }

            if (idseleccionado == -1) {
                // Nuevo registro
                accesosjava.NewPass(usuario, proveedor, password);
                Toast.makeText(formulario_gestor.this, "Contraseña guardada", Toast.LENGTH_SHORT).show();
            } else {
                // Actualizar registro existente
                accesosjava.editarDatos(idseleccionado, usuario, proveedor, password);
                Toast.makeText(formulario_gestor.this, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show();
            }

            limpiarCampos();
            finish();
        });

        //BOTON PARA ELIMINAR REGISTROS
        btn_eliminar.setOnClickListener(v -> {
            if (idseleccionado == -1) {
                Toast.makeText(formulario_gestor.this, "No se puede eliminar un registro que no ha sido guardado", Toast.LENGTH_SHORT).show();
                return;
            }

            accesosjava.eliminarDatos(idseleccionado);
            Toast.makeText(formulario_gestor.this, "Registro eliminado correctamente", Toast.LENGTH_SHORT).show();

            limpiarCampos();
            finish(); // Cierra la actividad y vuelve a MainActivity
        });

        // BOTÓN PARA SALIR
        btn_salir.setOnClickListener(v -> finish());

        //BOTON PARA VOLVER A LA ACCTIVIDAD ANTERIOR
        btn_back_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ACCION QUE REGRESA A LA ACCION ANTERIOR
                onBackPressed();
                Toast.makeText(formulario_gestor.this,"Back...",Toast.LENGTH_SHORT);
            }
        });
    }

    private void limpiarCampos() {
        txt_usuario.setText("");
        txt_proveedor.setText("");
        txt_password.setText("");
        txt_usuario.requestFocus();
    }
}
