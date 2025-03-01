package com.example.guardar_contraseas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private ListView listpass;
    private accesos accesosjava;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FloatingActionButton btn_agg = findViewById(R.id.btnfl_agg);
        accesosjava = new accesos(this);
        listpass = findViewById(R.id.lista_pass);
        ImageView btn_back_login = findViewById(R.id.btn_back_login);

        actualizarLista();

        btn_agg.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, formulario_gestor.class)));

        listpass.setOnItemClickListener((parent, view, position, id) -> {
            String obtenerPass = (String) parent.getItemAtPosition(position);
            String[] parts = obtenerPass.split(" - ");

            if (parts.length == 4) {
                try {
                    int idSeleccionado = Integer.parseInt(parts[0]);
                    Intent intent = new Intent(MainActivity.this, formulario_gestor.class);
                    intent.putExtra("id", idSeleccionado);
                    intent.putExtra("usuario", parts[1]);
                    intent.putExtra("proveedor", parts[2]);
                    intent.putExtra("password", parts[3]);
                    startActivity(intent);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Error al procesar el ID", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Formato incorrecto de los datos", Toast.LENGTH_SHORT).show();
            }
        });

        //ACCION PARA VOLVER A LA ACCION ANTERIOR-LOGIN
        btn_back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ACCION DE VOLVER ATAS
                Intent intent = new Intent(MainActivity.this, login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    private void actualizarLista() {
        List<String> passwordList = accesosjava.obtenerPassword();
        adapter = new ArrayAdapter<>(this, R.layout.item_personalizado, passwordList);
        listpass.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        actualizarLista();
    }
}
