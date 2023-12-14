package com.example.appmedicanmovilver2.retrofit.request;

import com.google.gson.annotations.SerializedName;

public class MedicoRequest {

    @SerializedName("idMedico")
    private Long idMedico;

    public MedicoRequest(Long idMedico) {
        this.idMedico = idMedico;
    }

    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
    }
}
