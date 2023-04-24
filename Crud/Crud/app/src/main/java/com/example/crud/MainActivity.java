package com.example.crud;

import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;

import com.example.crud.model.Empleado;
import com.example.crud.sinterface.CrudEmpleadoInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;





public class MainActivity extends AppCompatActivity {
    List<Empleado> listEmpleado;
    CrudEmpleadoInterface cruempleado;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getALL();
        //deleteEmpleado(Long.valueOf(1));
    }

    private void getALL(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        cruempleado = retrofit.create(CrudEmpleadoInterface.class);
        Call<List<Empleado>> call = cruempleado.getALL();

        call.enqueue(new Callback<List<Empleado>>() {
            @Override
            public void onResponse(Call<List<Empleado>> call, Response<List<Empleado>> response) {
                if (!response.isSuccessful()){
                    //System.out.println(response.message());
                    Log.e("Response: err", response.message());
                    return;
                }
                listEmpleado = response.body();
                //listEmpleado.forEach(p -> System.out.println(p.toString()));
                listEmpleado.forEach(p-> Log.i("Empleados:  ", p.toString()));
            }

            @Override
            public void onFailure(Call<List<Empleado>> call, Throwable t){

                //System.out.println(t.getMessage());
                Log.e("Throw error:", t.getMessage() );
            }
        });

    }

    private void getEmpleado(Long idEmpleado) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CrudEmpleadoInterface cruempleado = retrofit.create(CrudEmpleadoInterface.class);
        Call<Empleado> call = cruempleado.getEmpleado(idEmpleado);
        call.enqueue(new Callback<Empleado>() {
            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                if (!response.isSuccessful()) {
                    Log.e("Response: err", response.message());
                    return;
                }
                Empleado empleado = response.body();
                Log.i("Empleado obtenido: ", empleado.toString());
            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {
                Log.e("Throw error:", t.getMessage());
            }
        });
    }

    private void create(Empleado empleado) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CrudEmpleadoInterface cruempleado = retrofit.create(CrudEmpleadoInterface.class);
        Call<Empleado> call = cruempleado.create(empleado);
        call.enqueue(new Callback<Empleado>() {
            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                if (!response.isSuccessful()) {
                    Log.e("Response: err", response.message());
                    return;
                }
                Empleado createdEmpleado = response.body();
                Log.i("Empleado creado: ", createdEmpleado.toString());
            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {
                Log.e("Throw error:", t.getMessage());
            }
        });
    }

    private void update(Empleado empleado) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CrudEmpleadoInterface cruempleado = retrofit.create(CrudEmpleadoInterface.class);
        Call<Empleado> call = cruempleado.update(empleado.getId(), empleado);
        call.enqueue(new Callback<Empleado>() {
            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                if (!response.isSuccessful()) {
                    Log.e("Response: err", response.message());
                    return;
                }
                Empleado updatedEmpleado = response.body();
                Log.i("Empleado actualizado: ", updatedEmpleado.toString());
            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {
                Log.e("Throw error:", t.getMessage());
            }
        });
    }

    private void deleteEmpleado(Long idEmpleado){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        cruempleado = retrofit.create(CrudEmpleadoInterface.class);
        Call<Void> call = cruempleado.delete(idEmpleado);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Log.e("Response: err", response.message());
                    return;
                }
                Log.i("Delete status code: ", String.valueOf(response.code()));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Throw error:", t.getMessage() );
            }
        });
    }
}