package com.majada.jhona.myapplication;


public class Tarea{
    private  String titulo, estado, prioridad, fecha, hora;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tarea(String titulo, String estado, String prioridad, String fecha, String hora, int id) {
        this.titulo = titulo;
        this.estado = estado;
        this.prioridad = prioridad;
        this.fecha = fecha;
        this.hora = hora;
        this.id= id;
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
