package com.example.agendagamer.conexion;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.example.agendagamer.Menu;
import com.example.agendagamer.R;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class FindById extends AppCompatActivity {
    private ApiClient apiClient;
    private TextInputLayout id;
    private EditText i;
    private TextView txtres;
    private Button btnfind;

    private Button reg;

    private Button b;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        id = findViewById(R.id.id);
        i = id.getEditText();
        txtres = findViewById(R.id.txtfind);
        btnfind = findViewById(R.id.btfind);
        reg = findViewById(R.id.btnre);
        b = findViewById(R.id.borrar);

        btnfind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiClient = new ApiClient(FindById.this);
                String idStr = i.getText().toString();

                if (idStr.isEmpty()) {
                    Toast.makeText(FindById.this, "Ingrese un ID válido", Toast.LENGTH_SHORT).show();
                    return;
                }

                int id1 = Integer.parseInt(idStr);

                apiClient.findid(id1, new ApiClient.ApiResponseCallback() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response.toString());
                            int iden = jsonResponse.getInt("id");
                            String nombre = jsonResponse.getString("nombre");
                            int horasJugadas = jsonResponse.getInt("horasJugadas");
                            int logros = jsonResponse.getInt("logros");

                            String data = "ID : " + iden + "\nNombre: " + nombre +
                                    "\nHoras: " + horasJugadas + "\nLogros: " + logros;
                            txtres.setText(data);
                        } catch (JSONException e) {

                        }

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
                        Intent enlazar = new Intent(FindById.this, Menu.class);
                        startActivity(enlazar);
                        finish();
                    }
                },200);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtres.setText(null);
            }
        });
    }
}
