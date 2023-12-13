package com.example.appmedicanmovilver2.retrofit;

import com.example.appmedicanmovilver2.bd.entity.Usuario;
import com.example.appmedicanmovilver2.retrofit.response.MascotaResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MascotaService {

    @GET("mascotas/usuario/{id}")
    Call<List<MascotaResponse>> obtenerMascotasByUsuario(@Path("id") Long id);
}
