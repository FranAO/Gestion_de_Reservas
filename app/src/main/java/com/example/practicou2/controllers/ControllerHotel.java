package com.example.practicou2.controllers;

import com.example.practicou2.models.HabitacionDeluxe;
import com.example.practicou2.models.HabitacionEstandar;
import com.example.practicou2.models.Reserva;
import com.example.practicou2.models.Suite;

import java.util.ArrayList;

public class ControllerHotel {
    private static ControllerHotel instance;
    public ArrayList<Reserva> reservas = new ArrayList<>();

    private ControllerHotel()
    {
        reservas.add(new HabitacionEstandar("Juan Perez", "2025-04-01", 3, 100.0, "Estandar", 20.0));
        reservas.add(new HabitacionDeluxe("Maria Gomez", "2025-04-02", 5, 200.0, "Deluxe", true, 200.0));
        reservas.add(new Suite("Carlos Lopez", "2025-04-03", 7, 500.0, "Suite", 4, 30.0));
    }

    public static ControllerHotel getInstance()
    {
        if (instance == null) {
            instance = new ControllerHotel();
        }
        return instance;
    }

    public Reserva buscarReserva(String nombreHuesped, String tipoHabitacion) {
        for (Reserva reserva : reservas) {
            if (reserva.getNombreHuesped().equals(nombreHuesped) ||
                    reserva.getTipoHabitacion().equals(tipoHabitacion)) {
                return reserva;
            }
        }
        return null;
    }
}