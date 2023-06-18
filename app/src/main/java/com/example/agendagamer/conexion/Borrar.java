package com.example.agendagamer.conexion;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.example.agendagamer.Menu;
import com.example.agendagamer.R;

import org.json.JSONObject;

public class Borrar extends AppCompatActivity {

    private ApiClient apiClient;

    private EditText b;
    private Button btbor;

    private Button reg;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borr);
        b = findViewById(R.id.borid);
        btbor = findViewById(R.id.bor);
        reg = findViewById(R.id.reeg);

        btbor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiClient = new ApiClient(Borrar.this);
                String idStr = b.getText().toString();

// Verificar si el campo está vacío o no es numérico
                if (idStr.isEmpty()) {
                    Toast.makeText(Borrar.this, "Ingrese un ID válido", Toast.LENGTH_SHORT).show();
                    return;
                }

                int id = Integer.parseInt(idStr);

                apiClient.delete(id, new ApiClient.ApiResponseCallback() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        Toast.makeText(Borrar.this, "Borrado Correctamente", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(VolleyError error) {

                    }
                });

            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent enlazar = new Intent(Borrar.this, Menu.class);
                        startActivity(enlazar);
                        finish();
                    }
                },200);
            }
        });
    }
}
