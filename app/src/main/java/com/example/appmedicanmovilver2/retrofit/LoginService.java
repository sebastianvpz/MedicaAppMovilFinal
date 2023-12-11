package com.example.appmedicanmovilver2.retrofit;

import com.example.appmedicanmovilver2.retrofit.request.LoginRequest;
import com.example.appmedicanmovilver2.retrofit.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

}
