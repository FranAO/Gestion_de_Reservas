package com.example.practicou2.models;

public class HabitacionEstandar extends Reserva
{
    public double costoExtraDesayuno;

    public double getCostoExtraDesayuno() {
        return costoExtraDesayuno;
    }

    public HabitacionEstandar(String nombreHuesped, String fechaIngreso, int numeroNoches, double tarifaBase, String tipoHabitacion, double costoExtraDesayuno)
    {
        super(nombreHuesped, fechaIngreso, numeroNoches, tarifaBase, tipoHabitacion);
        this.costoExtraDesayuno = costoExtraDesayuno;
    }

    public double calcularTotal ()
    {
        return super.calcularTotal() + costoExtraDesayuno;
    }

    @Override
    public String mostrarDetalles()
    {
        return super.mostrarDetalles() + "\nCosto Extra Desayuno: " + costoExtraDesayuno;
    }
}
