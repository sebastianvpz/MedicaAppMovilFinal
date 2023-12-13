package com.example.appmedicanmovilver2.retrofit.request;

public class UsuarioRequest {

    private Long idUsuario;

    public UsuarioRequest(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
