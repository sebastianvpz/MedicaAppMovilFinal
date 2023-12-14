package com.example.appmedicanmovilver2.retrofit.response;

import com.google.gson.annotations.SerializedName;

public class ServicioConsResponse {

    @SerializedName("idServicio")
    private Long idServicio;

    @SerializedName("costo")
    private double costo;

    @SerializedName("descripcion")
    private String descripcion;

    @SerializedName("horario")
    private String horario;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("imagen")
    private String imagen;


    public Long getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
