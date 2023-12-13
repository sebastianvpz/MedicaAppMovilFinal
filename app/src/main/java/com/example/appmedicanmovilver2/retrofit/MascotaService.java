package com.example.appmedicanmovilver2.retrofit;

import com.example.appmedicanmovilver2.bd.entity.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MascotaService {

    @GET("mascotas/usuario/{id}")
    Call<Void> obtenerMascotasByUsuario(@Path("id") Long id, @Body Usuario usuario);
}
