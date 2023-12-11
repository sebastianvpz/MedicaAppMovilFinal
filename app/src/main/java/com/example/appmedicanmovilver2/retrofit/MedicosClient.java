package com.example.appmedicanmovilver2.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MedicosClient {
    private static final String BASE_URL ="http://10.0.2.2:8080/api/";

    private  MedicosService medicosService;

    public MedicosClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        medicosService = retrofit.create(MedicosService.class);
    }

    public MedicosService getInstace(){
        return medicosService;
    }
}
