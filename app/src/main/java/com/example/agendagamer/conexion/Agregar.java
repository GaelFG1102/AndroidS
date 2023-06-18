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

import org.json.JSONException;
import org.json.JSONObject;

public class Agregar extends AppCompatActivity {
    private EditText ide;
    private EditText nom;
    private EditText horas;
    private EditText log;

    private Button btnagre;

    private Button btregre;

    private Button limp;

    private ApiClient apiClient;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        ide = findViewById(R.id.etxID);
        nom = findViewById(R.id.etxNombre);
        horas = findViewById(R.id.etxHoras);
        log = findViewById(R.id.etxLog);
        btnagre = findViewById(R.id.btnagregrar);
        btregre = findViewById(R.id.btnreg);
        limp  = findViewById(R.id.btlimp);

        btnagre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Long id = Long.valueOf(ide.getText().toString());
                    String nombre = nom.getText().toString();
                    int hrs = Integer.parseInt(horas.getText().toString());
                    int logros = Integer.parseInt(log.getText().toString());

                    // Verificar si los campos están vacíos
                    if (nombre.isEmpty()) {
                        Toast.makeText(Agregar.this, "Ingrese un nombre válido", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Crear el objeto JSON con los datos
                    JSONObject nuevoRegistro = new JSONObject();
                    nuevoRegistro.put("id", id);
                    nuevoRegistro.put("nombre", nombre);
                    nuevoRegistro.put("horasJugadas", hrs);
                    nuevoRegistro.put("logros", logros);

                    // Enviar la solicitud a la API
                    apiClient = new ApiClient(Agregar.this);
                    apiClient.agre(nuevoRegistro, new ApiClient.ApiResponseCallback() {
                        @Override
                        public void onSuccess(JSONObject response) {
                            Toast.makeText(Agregar.this, "Agregado Correctamente", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onError(VolleyError error) {
                            Toast.makeText(Agregar.this, "ERROR de solicitud", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (JSONException e) {
                    Toast.makeText(Agregar.this, "ERROR", Toast.LENGTH_SHORT).show();
                } catch (NumberFormatException e) {
                    Toast.makeText(Agregar.this, "Ingrese valores numéricos válidos", Toast.LENGTH_SHORT).show();
                }


            }
        });
        btregre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent enlazar = new Intent(Agregar.this, Menu.class);
                        startActivity(enlazar);
                        finish();
                    }
                },200);
            }
        });

        limp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ide.setText(null);
                nom.setText(null);
                horas.setText(null);
                log.setText(null);
            }
        });
    }
}
