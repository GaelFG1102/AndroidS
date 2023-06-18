package com.example.agendagamer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agendagamer.conexion.Actualizar;
import com.example.agendagamer.conexion.Agregar;
import com.example.agendagamer.conexion.Borrar;
import com.example.agendagamer.conexion.FindById;
import com.example.agendagamer.conexion.Mostrar;

public class Menu extends AppCompatActivity {

    private Button mostrar;
    private Button bus;
    private Button btnagre;

    private Button btnac;

    private Button btnbor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mostrar = findViewById(R.id.mostrar);
        bus = findViewById(R.id.btbus);
        btnagre = findViewById(R.id.btnagre);
        btnac = findViewById(R.id.btac);
        btnbor = findViewById(R.id.btnBorrar);

        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent enlazar = new Intent(Menu.this, Mostrar.class);
                        startActivity(enlazar);
                        finish();
                    }
                },500);
            }
        });

        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent enlazar = new Intent(Menu.this, FindById.class);
                        startActivity(enlazar);
                        finish();
                    }
                },200);
            }
        });

        btnagre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent enlazar = new Intent(Menu.this, Agregar.class);
                        startActivity(enlazar);
                        finish();
                    }
                },200);
            }
        });

        btnbor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent enlazar = new Intent(Menu.this, Borrar.class);
                        startActivity(enlazar);
                        finish();
                    }
                },200);
            }
        });

        btnac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent enlazar = new Intent(Menu.this, Actualizar.class);
                        startActivity(enlazar);
                        finish();
                    }
                },200);
            }
        });
    }
}
