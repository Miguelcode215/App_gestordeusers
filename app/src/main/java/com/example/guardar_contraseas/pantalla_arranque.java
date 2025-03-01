package com.example.guardar_contraseas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class pantalla_arranque extends AppCompatActivity {

    private static final int pantalla_arranque = 3000; //VARIABLE QUE INICIALIZA EL TIEMPO DE DURACION

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_arranque); // Asegúrate de usar tu layout de splash

        // Retrasar la transición a la siguiente actividad
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(pantalla_arranque.this, login.class);
                startActivity(intent);
                finish(); // Cierra el SplashActivity para que no vuelva atrás
            }
        }, pantalla_arranque);
    }
}