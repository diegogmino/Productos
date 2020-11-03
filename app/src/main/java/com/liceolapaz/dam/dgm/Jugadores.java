package com.liceolapaz.dam.dgm;

public class Jugadores {

    public int codigo;
    public String nombre;
    public float precio;
    public String posicion;
    public int puntos;

    public Jugadores(int codigo, String nombre, float precio, String posicion, int puntos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.posicion = posicion;
        this.puntos = puntos;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
