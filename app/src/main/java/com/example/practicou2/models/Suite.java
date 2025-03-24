package com.example.practicou2.models;

public class Suite extends Reserva
{
    public int numeroSalas;
    public double servicioPersonalizado;

    public int getNumeroSalas() {
        return numeroSalas;
    }

    public double getServicioPersonalizado() {
        return servicioPersonalizado;
    }

    public Suite(String nombreHuesped, String fechaIngreso, int numeroNoches, double tarifaBase, String tipoHabitacion, int numeroSalas, double servicioPersonalizado)
    {
        super(nombreHuesped, fechaIngreso, numeroNoches, tarifaBase, tipoHabitacion);
        this.numeroSalas = numeroSalas;
        this.servicioPersonalizado = servicioPersonalizado;
    }

    public double calcularTotal ()
    {
        return tarifaBase * numeroNoches + (numeroSalas * 100) + servicioPersonalizado;
    }

    @Override
    public String mostrarDetalles()
    {
        return super.mostrarDetalles() + "\nNúmero de Salas: " + numeroSalas + "\nServicio Personalizado: " + servicioPersonalizado;
    }
}
