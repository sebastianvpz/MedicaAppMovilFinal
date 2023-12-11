package com.example.appmedicanmovilver2.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistroClient {

    private static final String BASE_URL ="http://10.0.2.2:8080/api/";

    private  RegistroService registroService;

    public RegistroClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        registroService = retrofit.create(RegistroService.class);
    }

    public RegistroService getInstace(){
        return registroService;
    }
}
