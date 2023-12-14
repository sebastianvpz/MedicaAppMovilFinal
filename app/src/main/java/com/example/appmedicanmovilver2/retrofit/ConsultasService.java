package com.example.appmedicanmovilver2.retrofit;

import com.example.appmedicanmovilver2.retrofit.response.ConsultaResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ConsultasService {

    @GET("consultas/usuario/{idUsuario}")
    Call<List<ConsultaResponse>> getConsultasByUsuario(@Path("idUsuario") Long idUsuario);
}
