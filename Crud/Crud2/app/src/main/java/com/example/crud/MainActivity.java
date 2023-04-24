package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.crud.intefaces.CrudEmpleadoInterface;
import com.example.crud.model.Empleado;
import com.example.crud.utils.Constants;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<Empleado> listEmpleado;
    CrudEmpleadoInterface cruempleado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAllEmpleados();
    }

    private void getAllEmpleados() {
        final String LOG_TAG = "getAllEmpleados";
        final String ERROR_MESSAGE = "Error al obtener los empleados: ";
        final String SUCCESS_MESSAGE = "Empleados obtenidos correctamente. Cantidad: %d.";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CrudEmpleadoInterface cruempleado = retrofit.create(CrudEmpleadoInterface.class);
        Call<List<Empleado>> call = cruempleado.getALL();

        call.enqueue(new Callback<List<Empleado>>() {
            @Override
            public void onResponse(Call<List<Empleado>> call, Response<List<Empleado>> response) {
                if (!response.isSuccessful()) {
                    Log.e(LOG_TAG, ERROR_MESSAGE + response.message());
                    return;
                }
                List<Empleado> empleados = response.body();
                Log.i(LOG_TAG, String.format(SUCCESS_MESSAGE, empleados.size()));
                empleados.stream().forEach(empleado -> Log.i(LOG_TAG, empleado.toString()));
            }

            @Override
            public void onFailure(Call<List<Empleado>> call, Throwable t) {
                Log.e(LOG_TAG, ERROR_MESSAGE + t.getMessage());
            }
        });
    }

    private Empleado getEmpleado(Long idEmpleado) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CrudEmpleadoInterface cruempleado = retrofit.create(CrudEmpleadoInterface.class);
        Call<Empleado> call = cruempleado.getEmpleado(idEmpleado);
        Response<Empleado> response = call.execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            throw new IOException("Error al obtener el empleado: " + response.message());
        }
    }

    private Empleado createEmpleado(Empleado empleado) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CrudEmpleadoInterface cruempleado = retrofit.create(CrudEmpleadoInterface.class);
        Call<Empleado> call = cruempleado.createEmpleado(empleado);
        try {
            Response<Empleado> response = call.execute();
            if (!response.isSuccessful()) {
                Log.e("Response: err", response.message());
                return null;
            }
            Empleado createdEmpleado = response.body();
            Log.i("Empleado creado: ", createdEmpleado.toString());
            return createdEmpleado;
        } catch (IOException e) {
            Log.e("Throw error:", e.getMessage());
            return null;
        }
    }

    private String updateEmpleado(Empleado empleado) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CrudEmpleadoInterface cruempleado = retrofit.create(CrudEmpleadoInterface.class);
        Call<Empleado> call = cruempleado.updateEmpleado(empleado.getId(), empleado);
        try {
            Response<Empleado> response = call.execute();
            if (response.isSuccessful()) {
                Empleado updatedEmpleado = response.body();
                return "Empleado actualizado: " + updatedEmpleado.toString();
            } else {
                return "Error al actualizar empleado: " + response.message();
            }
        } catch (IOException e) {
            return "Error al actualizar empleado: " + e.getMessage();
        }
    }

    private String deleteEmpleado(Long idEmpleado){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        cruempleado = retrofit.create(CrudEmpleadoInterface.class);
        Call<String> call = cruempleado.deleteEmpleado(idEmpleado);
        try {
            Response<String> response = call.execute();
            if (!response.isSuccessful()) {
                return "Error: " + response.message();
            }
            return "Delete status code: " + String.valueOf(response.code());
        } catch (IOException e) {
            return "Exception thrown: " + e.getMessage();
        }
    }
}