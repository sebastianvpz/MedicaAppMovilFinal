package com.example.appmedicanmovilver2.retrofit;

import com.example.appmedicanmovilver2.retrofit.request.RegistroRequest;
import com.example.appmedicanmovilver2.retrofit.response.RegistroResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegistroService {

    @POST("usuarios")
    Call<RegistroResponse> registro(@Body RegistroRequest registroRequest);
}
