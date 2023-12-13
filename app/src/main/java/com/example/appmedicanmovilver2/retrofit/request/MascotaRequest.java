package com.example.appmedicanmovilver2.retrofit.request;

public class MascotaRequest {
    private int edad;
    private String especie;
    private String nombre;
    private String sexo;
    private UsuarioRequest usuario;


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

    public UsuarioRequest getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioRequest usuario) {
        this.usuario = usuario;
    }
}
