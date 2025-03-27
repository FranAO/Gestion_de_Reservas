package com.example.practicou2.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practicou2.R;
import com.example.practicou2.controllers.ControllerHotel;
import com.example.practicou2.models.Reserva;

public class BuscarReserva extends AppCompatActivity
{
    public EditText etBuscar;
    public TextView tvResultado;
    public ControllerHotel controllerHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_reserva);

        controllerHotel = ControllerHotel.getInstance();

        init();
    }

    public void init()
    {
        etBuscar = findViewById(R.id.etBuscar);
        tvResultado = findViewById(R.id.tvResultado);
        Button btnBuscar = findViewById(R.id.btnBuscar);

        btnBuscar.setOnClickListener(v -> {
            String textoBusqueda = etBuscar.getText().toString().trim();

            Reserva reservaEncontrada = controllerHotel.buscarReserva(textoBusqueda, textoBusqueda);

            if (reservaEncontrada != null)
            {
                tvResultado.setText(reservaEncontrada.mostrarDetalles() + "\nPrecio: " + reservaEncontrada.calcularTotal());
            } else
            {
                tvResultado.setText("No se encontró ninguna reserva.");
            }
        });
    }
}