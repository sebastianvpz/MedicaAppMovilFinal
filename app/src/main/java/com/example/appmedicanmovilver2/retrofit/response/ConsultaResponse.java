package com.example.appmedicanmovilver2.retrofit.response;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class ConsultaResponse {

    @SerializedName("idConsulta")
    private Long idConsulta;

    @SerializedName("descripcion")
    private String descripcion;

    @SerializedName("diagnostico")
    private String diagnostico;

    @SerializedName("fecha")
    private String fecha;

    @SerializedName("razon")
    private String razon;

    @SerializedName("mascota")
    private MascotaConsResponse mascota;

    @SerializedName("servicio")
    private ServicioConsResponse servicio;

    public MascotaConsResponse getMascota() {
        return mascota;
    }

    public void setMascota(MascotaConsResponse mascota) {
        this.mascota = mascota;
    }

    public ServicioConsResponse getServicio() {
        return servicio;
    }

    public void setServicio(ServicioConsResponse servicio) {
        this.servicio = servicio;
    }

    public Long getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Long idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }


}
