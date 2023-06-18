package com.example.agendagamer.conexion;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.example.agendagamer.Menu;
import com.example.agendagamer.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.PhantomReference;

public class Actualizar extends AppCompatActivity {

    private Spinner opac;
    private EditText txtdato;
    private EditText idd;
    private Button btn;

    private Button reg;

    private Button bor;

    String op [] = {
            "Horas",
            "Logros"
    };

    private ApiClient apiClient;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac);
        opac = findViewById(R.id.spop);
        txtdato = findViewById(R.id.edtDato);
        idd = findViewById(R.id.ID);
        btn = findViewById(R.id.acb);
        reg = findViewById(R.id.reg);
        bor = findViewById(R.id.bor2);


        // Crear un ArrayAdapter utilizando un array de opciones
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                op
        );

        // Especificar el diseño para las opciones desplegables
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asignar el adaptador al spinner
        opac.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (opac.getSelectedItemPosition() == 0) {
                    apiClient = new ApiClient(Actualizar.this);
                    int id;
                    int dat;

                    // Verificar si los campos están vacíos o no son numéricos
                    try {
                        id = Integer.parseInt(idd.getText().toString());
                        dat = Integer.parseInt(txtdato.getText().toString());
                    } catch (NumberFormatException e) {
                        Toast.makeText(Actualizar.this, "Ingrese valores numéricos válidos", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    try {
                        JSONObject valoresActualizados = new JSONObject();
                        valoresActualizados.put("horasJugadas", dat);

                        // Luego, llamas al método actualizarJuego pasando el ID y el objeto valoresActualizados
                        apiClient.actualizarJuego(id, "horas", valoresActualizados, new ApiClient.ApiResponseCallback() {
                            @Override
                            public void onSuccess(JSONObject response) {
                                Toast.makeText(Actualizar.this, "Actualizado Correctamente", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onError(VolleyError error) {
                                Toast.makeText(Actualizar.this, "No se pudo actualizar Correctamente", Toast.LENGTH_LONG).show();
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    apiClient = new ApiClient(Actualizar.this);
                    int id;
                    int dat;

                    // Verificar si los campos están vacíos o no son numéricos
                    try {
                        id = Integer.parseInt(idd.getText().toString());
                        dat = Integer.parseInt(txtdato.getText().toString());
                    } catch (NumberFormatException e) {
                        Toast.makeText(Actualizar.this, "Ingrese valores numéricos válidos", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    try {
                        JSONObject valoresActualizados = new JSONObject();
                        valoresActualizados.put("logros", dat);

                        // Luego, llamas al método actualizarJuego pasando el ID y el objeto valoresActualizados
                        apiClient.actualizarJuego2(id, "logros", valoresActualizados, new ApiClient.ApiResponseCallback() {
                            @Override
                            public void onSuccess(JSONObject response) {
                                Toast.makeText(Actualizar.this, "Actualizado Correctamente", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onError(VolleyError error) {
                                Toast.makeText(Actualizar.this, "No se pudo actualizar Correctamente", Toast.LENGTH_LONG).show();
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent enlazar = new Intent(Actualizar.this, Menu.class);
                        startActivity(enlazar);
                        finish();
                    }
                },200);
            }
        });

        bor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtdato.setText(null);
                idd.setText(null);
            }
        });
    }
}
