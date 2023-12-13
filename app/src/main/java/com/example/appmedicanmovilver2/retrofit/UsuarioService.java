package com.example.appmedicanmovilver2.retrofit;

import com.example.appmedicanmovilver2.bd.entity.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UsuarioService {

    @PUT("usuarios/{id}")
    Call<Void> actualizarUsuario(@Path("id") Long id, @Body Usuario usuario);
}
