package com.example.crud.sinterface;

import com.example.crud.model.Empleado;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CrudEmpleadoInterface {

    @GET("/consultarALL")
    Call<List<Empleado>> getALL();
    @GET("empleados/{id}")
    Call<Empleado> getEmpleado(@Path("id") Long id);
    @POST("/guardar")
    Call<Empleado> create(@Body Empleado empleado);
    @PATCH("/user/{id}")
    Call<Empleado> update(@Path("id") Long id, @Body Empleado empleado);

    @DELETE("/user/{id}")
    Call<Void> delete(@Path("id") Long idEmpleado);
}