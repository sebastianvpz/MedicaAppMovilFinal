package com.example.appmedicanmovilver2.retrofit.response;

import com.google.gson.annotations.SerializedName;

public class CitaResponse {

    @SerializedName("idCita")
    private Long idCita;

    @SerializedName("fecha_hora")
    private String fechaHora;

    // Otras propiedades de la cita, como medico y servicio

    public Long getIdCita() {
        return idCita;
    }

    public String getFechaHora() {
        return fechaHora;
    }

}
