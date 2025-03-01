package com.example.guardar_contraseas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class login extends AppCompatActivity {

    private final String Nombre_user = "1526631";
    private final String Password = "miguel@s";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //INICIALIZAMOS LOS EDIT TEXT
        TextView txt_usuario = findViewById(R.id.txt_user);
        TextView txt_pass = findViewById(R.id.txt_password);
        Button btn_acceder = findViewById(R.id.btn_Login);
        ImageView btn_back = findViewById(R.id.btn_back);

        //ACCION DEL BOTON PARA ACCEDER
        btn_acceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuarioinput = txt_usuario.getText().toString();
                String passinput = txt_pass.getText().toString();


                if (usuarioinput.equals(Nombre_user) && passinput.equals(Password)){
                    Toast.makeText(login.this,"INISIO DE SESION EXITOSA",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(login.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(login.this,"CREDENCIALES INVALIDAS",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //CIERRA LA APLICACION
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }
}