package com.example.appmedicanmovilver2.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiciosClient {

    private static final String BASE_URL ="http://10.0.2.2:8080/api/";

    private  ServiciosService serviciosService;

    public ServiciosClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serviciosService = retrofit.create(ServiciosService.class);
    }

    public ServiciosService getInstace(){
        return serviciosService;
    }
}
