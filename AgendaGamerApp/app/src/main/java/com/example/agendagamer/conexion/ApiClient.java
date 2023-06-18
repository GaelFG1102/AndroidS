package com.example.agendagamer.conexion;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiClient {
    private Context context;
    private Api apiConnection;

    public ApiClient(Context context) {
        this.context = context;
        apiConnection = apiConnection.getInstance(context);
    }

    public void findid(final int id, final ApiResponseCallback callback) {
        String url = "http://172.20.10.2:8080/bdgamer/finbyid/" + id;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Procesa la respuesta JSON y llamar al callback
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Llama al callback con el error
                        callback.onError(error);
                    }
                });

        // Agrega la solicitud a la cola de solicitudes de Volley
        apiConnection.getRequestQueue().add(request);
    }

    public void mostrar(final ApiResponseCallback2 callback) {
        String url = "http://172.20.10.2:8080/bdgamer/mostrar-todos";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Procesa la respuesta JSON y llama al callback
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Llama al callback con el error
                        callback.onError(error);
                    }
                });

        // Agrega la solicitud a la cola de solicitudes de Volley
        apiConnection.getRequestQueue().add(request);
    }

    public void agre(final JSONObject nuevoRegistro, final ApiResponseCallback callback){
        String url = "http://172.20.10.2:8080/bdgamer/guardar";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, nuevoRegistro,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // El registro se ha agregado correctamente
                        // Puedes realizar las acciones necesarias después de agregar el registro
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar el error de la solicitud
                        callback.onError(error);
                    }
                });

// Agregar la solicitud a la cola de solicitudes de Volley
        apiConnection.getRequestQueue().add(request);
    }

    public void actualizarJuego(final int id,final String dato, final JSONObject valoresActualizados, final ApiResponseCallback callback) {
        String url = "http://172.20.10.2:8080/bdgamer/horas/" + id;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, valoresActualizados,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // La actualización se ha realizado correctamente
                        // Puedes realizar las acciones necesarias después de la actualización
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar el error de la solicitud
                        callback.onError(error);
                    }
                });

        // Agregar la solicitud a la cola de solicitudes de Volley
        apiConnection.getRequestQueue().add(request);
    }

    public void actualizarJuego2(final int id,final String dato, final JSONObject valoresActualizados, final ApiResponseCallback callback) {
        String url = "http://172.20.10.2:8080/bdgamer/logros/" + id;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, valoresActualizados,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // La actualización se ha realizado correctamente
                        // Puedes realizar las acciones necesarias después de la actualización
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar el error de la solicitud
                        callback.onError(error);
                    }
                });

        // Agregar la solicitud a la cola de solicitudes de Volley
        apiConnection.getRequestQueue().add(request);
    }


    public void delete(final int id, final ApiResponseCallback callback) {
        String url = "http://10.0.8.57:8080/bdgamer/borrar/" + id;
        StringRequest request = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Llama al callback con una respuesta vacía
                        callback.onSuccess(null);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Llama al callback con el error
                        callback.onError(error);
                    }
                });

        // Agrega la solicitud a la cola de solicitudes de Volley
        apiConnection.getRequestQueue().add(request);
    }

    public interface ApiResponseCallback {
        void onSuccess(JSONObject response);

        void onError(VolleyError error);
    }

    public interface ApiResponseCallback2 {
        void onSuccess(JSONArray response);

        void onError(VolleyError error);
    }
}

