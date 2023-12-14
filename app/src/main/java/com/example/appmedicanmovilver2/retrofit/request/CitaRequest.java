package com.example.appmedicanmovilver2.retrofit.request;

import com.google.gson.annotations.SerializedName;

public class CitaRequest {

    @SerializedName("fecha_hora")
    private String fechaHora;

    @SerializedName("medico")
    private MedicoRequest medico;

    @SerializedName("servicio")
    private ServicioRequest servicio;

    public CitaRequest(String fechaHora, MedicoRequest medico, ServicioRequest servicio) {
        this.fechaHora = fechaHora;
        this.medico = medico;
        this.servicio = servicio;
    }


    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public MedicoRequest getMedico() {
        return medico;
    }

    public void setMedico(MedicoRequest medico) {
        this.medico = medico;
    }

    public ServicioRequest getServicio() {
        return servicio;
    }

    public void setServicio(ServicioRequest servicio) {
        this.servicio = servicio;
    }
}
