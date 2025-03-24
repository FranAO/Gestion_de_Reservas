package com.example.practicou2.models;

public class HabitacionDeluxe extends Reserva
{
    public boolean accesoLounge;
    public double servicioGourmet;

    public boolean isAccesoLounge() {
        return accesoLounge;
    }

    public double isServicioGourmet() {
        return servicioGourmet;
    }

    public HabitacionDeluxe(String nombreHuesped, String fechaIngreso, int numeroNoches, double tarifaBase, String tipoHabitacion, boolean accesoLounge, double servicioGourmet)
    {
        super(nombreHuesped, fechaIngreso, numeroNoches, tarifaBase, tipoHabitacion);
        this.accesoLounge = accesoLounge;
        this.servicioGourmet = servicioGourmet;
    }

    public double calcularTotal ()
    {
        return tarifaBase * numeroNoches + (accesoLounge ? 50 : 0) + servicioGourmet;
    }

    @Override
    public String mostrarDetalles()
    {
        return super.mostrarDetalles() + "\nAcceso Lounge: " + (accesoLounge ? "Sí" : "No") + "\nServicio Gourmet: " + servicioGourmet;
    }
}
