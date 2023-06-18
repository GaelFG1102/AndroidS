package com.example.agendagamer.conexion;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.example.agendagamer.Menu;
import com.example.agendagamer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Mostrar extends AppCompatActivity {
    private ApiClient apiClient;
    private Button btnmos;

    private TextView txtres;

    private Button re;

    private Button limp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        btnmos = findViewById(R.id.BtnMos);
        txtres = findViewById(R.id.txtmos);
        re = findViewById(R.id.btnregresar);
        limp  = findViewById(R.id.btnlimpia);

        btnmos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiClient = new ApiClient(Mostrar.this);
                apiClient.mostrar(new ApiClient.ApiResponseCallback2() {
                    @Override
                    public void onSuccess(JSONArray response) {
                        try {
                            StringBuilder data = new StringBuilder();

                            if (response.length() == 0) {
                                // No hay registros disponibles
                                data.append("No hay registros disponibles");
                            } else {
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject jsonObject = response.getJSONObject(i);

                                    // Verificar si los campos en el objeto JSON están vacíos
                                    if (!jsonObject.isNull("id") && !jsonObject.isNull("nombre")
                                            && !jsonObject.isNull("horasJugadas") && !jsonObject.isNull("logros")) {

                                        // Obtener los valores de los campos en cada objeto JSON
                                        Long id = jsonObject.getLong("id");
                                        String nombre = jsonObject.getString("nombre");
                                        int horasJugadas = jsonObject.getInt("horasJugadas");
                                        int logros = jsonObject.getInt("logros");

                                        // Construir la cadena de texto para cada registro
                                        String recordData = "ID: " + id + "\nNombre: " + nombre +
                                                "\nHoras jugadas: " + horasJugadas + "\nLogros: " + logros;

                                        // Agregar el registro a la cadena de texto general
                                        data.append(recordData).append("\n\n");
                                    }
                                }
                            }

                            // Verificar si no se encontraron registros válidos
                            if (data.length() == 0) {
                                data.append("No se encontraron registros válidos");
                            }

                            // Asigna el texto al TextView
                            txtres.setText(data.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                            txtres.setText("Error al procesar la respuesta del servidor");
                        }
                    }

                    @Override
                    public void onError(VolleyError error) {
                        // Maneja el error de la solicitud
                        String errorMessage = "Error de solicitud";
                        if (error != null && error.getMessage() != null) {
                            errorMessage = error.getMessage();
                        }
                        txtres.setText(errorMessage);
                    }
                });

            }
        });

        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent enlazar = new Intent(Mostrar.this, Menu.class);
                        startActivity(enlazar);
                        finish();
                    }
                },200);
            }
        });

        limp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtres.setText(null);
            }
        });

    }
}
