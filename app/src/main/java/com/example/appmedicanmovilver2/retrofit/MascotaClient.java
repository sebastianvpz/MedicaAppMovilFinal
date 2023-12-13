package com.example.appmedicanmovilver2.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MascotaClient {

    private static final String BASE_URL = "http://10.0.2.2:8080/api/";

    private MascotaService mascotaService;

    public MascotaClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mascotaService = retrofit.create(MascotaService.class);
    }

    public MascotaService getMascotaService() {
        return mascotaService;
    }
}
