package com.example.appmedicanmovilver2.retrofit;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConsultaClient {

    private static final String BASE_URL ="http://10.0.2.2:8080/api/";

    private  ConsultasService consultasService;

    public ConsultaClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        consultasService = retrofit.create(ConsultasService.class);
    }

    public ConsultasService getInstace(){
        return consultasService;
    }


}
