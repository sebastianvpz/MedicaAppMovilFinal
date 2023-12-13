package com.example.appmedicanmovilver2.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsuarioClient {

    private static final String BASE_URL = "http://10.0.2.2:8080/api/";

    private UsuarioService usuarioService;

    public UsuarioClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        usuarioService = retrofit.create(UsuarioService.class);
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }
}
