package com.example.practicou2.models;

public class Reserva
{
    public String nombreHuesped;
    public String fechaIngreso;
    public int numeroNoches;
    public double tarifaBase;
    public String tipoHabitacion;

    public String getNombreHuesped() {
        return nombreHuesped;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public int getNumeroNoches() {
        return numeroNoches;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public Reserva(String nombreHuesped, String fechaIngreso, int numeroNoches, double tarifaBase, String tipoHabitacion)
    {
        this.nombreHuesped = nombreHuesped;
        this.fechaIngreso = fechaIngreso;
        this.numeroNoches = numeroNoches;
        this.tarifaBase = tarifaBase;
        this.tipoHabitacion = tipoHabitacion;
    }
    public double calcularTotal ()
    {
        return tarifaBase;
    }

    public String mostrarDetalles ()
    {
        return "Nombre Huésped: " + nombreHuesped + "\nFecha de Ingreso: " + fechaIngreso + "\nNúmero de Noches: " + numeroNoches + "\nTarifa Base: " + tarifaBase + "\nTipo de Habitación: " + tipoHabitacion;
    }
}
