package com.example.appmedicanmovilver2.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginClient {

    private static final String BASE_URL ="http://10.0.2.2:8080/api/";

    private  LoginService loginService;

    public LoginClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        loginService = retrofit.create(LoginService.class);
    }

    public LoginService getInstace(){
        return loginService;
    }

}
