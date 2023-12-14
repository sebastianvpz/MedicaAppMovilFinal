package com.example.appmedicanmovilver2.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CitaClient {


    private static final String BASE_URL = "http://10.0.2.2:8080/api/";

    private CitaService citaService;

    public CitaClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        citaService = retrofit.create(CitaService.class);
    }

    public CitaService getInstance() {
        return citaService;
    }
}
