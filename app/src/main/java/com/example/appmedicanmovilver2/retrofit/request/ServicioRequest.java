package com.example.appmedicanmovilver2.retrofit.request;

import com.google.gson.annotations.SerializedName;

public class ServicioRequest {

    @SerializedName("idServicio")
    private Long idServicio;

    public ServicioRequest(Long idServicio) {
        this.idServicio = idServicio;
    }

    public Long getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }
}
