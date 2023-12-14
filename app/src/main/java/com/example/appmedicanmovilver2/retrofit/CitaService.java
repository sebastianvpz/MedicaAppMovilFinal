package com.example.appmedicanmovilver2.retrofit;

import com.example.appmedicanmovilver2.retrofit.request.CitaRequest;
import com.example.appmedicanmovilver2.retrofit.response.CitaResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CitaService {

    @POST("citas")
    Call<CitaResponse> createCita(@Body CitaRequest citaRequest);
}
