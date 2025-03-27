package com.example.practicou2.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practicou2.R;
import com.example.practicou2.controllers.ControllerHotel;
import com.example.practicou2.models.Reserva;

import java.util.ArrayList;

public class MostrarReserva extends AppCompatActivity {

    private Spinner spinnerReservas;
    private TextView tvResultado;
    private Button btnMostrar;
    private ArrayAdapter<String> spinnerAdapter;
    private ArrayList<String> nombresReservas = new ArrayList<>();
    private ControllerHotel controllerHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_reserva);

        controllerHotel = ControllerHotel.getInstance();
        init();
    }

    private void init()
    {
        spinnerReservas = findViewById(R.id.spinnerReservas);
        tvResultado = findViewById(R.id.tvResultado);
        btnMostrar = findViewById(R.id.btnMostrar);

        actualizarListaReservas();

        btnMostrar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mostrarDetallesReservaSeleccionada();
            }
        });
    }

    private void actualizarListaReservas()
    {
        nombresReservas.clear();
        for (Reserva reserva : controllerHotel.reservas)
        {
            nombresReservas.add(reserva.getNombreHuesped() + " - " + reserva.getTipoHabitacion());
        }

        if (spinnerAdapter == null)
        {
            spinnerAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item,
                    nombresReservas);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerReservas.setAdapter(spinnerAdapter);
        } else
        {
            spinnerAdapter.notifyDataSetChanged();
        }
    }
    private void mostrarDetallesReservaSeleccionada()
    {
        int posicionSeleccionada = spinnerReservas.getSelectedItemPosition();

        if (posicionSeleccionada >= 0 && posicionSeleccionada < controllerHotel.reservas.size())
        {
            Reserva reserva = controllerHotel.reservas.get(posicionSeleccionada);
            String detalles = "Detalles de la Reserva:\n\n" + reserva.mostrarDetalles() + "\nPrecio Total: $" + reserva.calcularTotal();
            tvResultado.setText(detalles);
        }
    }
}