package com.majada.jhona.myapplication;


public class Tarea{
    private  String titulo, estado, prioridad, fecha, hora;

    public Tarea(String titulo, String estado, String prioridad, String fecha, String hora) {
        this.titulo = titulo;
        this.estado = estado;
        this.prioridad = prioridad;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getTitulo() {

        return titulo;
    }
}
