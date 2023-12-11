package com.example.appmedicanmovilver2.retrofit;

import com.example.appmedicanmovilver2.retrofit.response.MedicosResponse;
import com.example.appmedicanmovilver2.retrofit.response.ServiciosResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MedicosService {

    @GET("medicos")
    Call<List<MedicosResponse>> listarMedicos();
}
