package com.example.appmedicanmovilver2.retrofit.response;

import com.google.gson.annotations.SerializedName;

public class MascotaConsResponse {


    @SerializedName("idMascota")
    private Long idMascota;

    @SerializedName("edad")
    private int edad;

    @SerializedName("especie")
    private String especie;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("sexo")
    private String sexo;

    public Long getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Long idMascota) {
        this.idMascota = idMascota;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
