package com.example.practicou2.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practicou2.R;
import com.example.practicou2.controllers.ControllerHotel;
import com.example.practicou2.models.HabitacionDeluxe;
import com.example.practicou2.models.HabitacionEstandar;
import com.example.practicou2.models.Suite;

public class AgregarReserva extends AppCompatActivity
{
    private EditText etNombreHuesped;
    private EditText etFechaIngreso;
    private EditText etNumeroNoches;
    private EditText etTarifaBase;
    private EditText etCostoExtraDesayuno;
    private EditText etServicioGourmet;
    private EditText etNumeroSalas;
    private EditText etServicioPersonalizado;
    private Spinner spinnerAccesoLounge;
    private Spinner spinnerHabitacion;
    private ControllerHotel controllerHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_reserva);

        controllerHotel = ControllerHotel.getInstance();

        init();
        setupSpinners();
        setupButtonListeners();
    }

    private void init()
    {
        spinnerHabitacion = findViewById(R.id.spinnerTipoHabitacion);
        etNombreHuesped = findViewById(R.id.etNombreHuesped);
        etFechaIngreso = findViewById(R.id.etFechaIngreso);
        etNumeroNoches = findViewById(R.id.etNumeroNoches);
        etTarifaBase = findViewById(R.id.etTarifaBase);
        etCostoExtraDesayuno = findViewById(R.id.etCostoExtraDesayuno);
        spinnerAccesoLounge = findViewById(R.id.spinnerAccesoLounge);
        etServicioGourmet = findViewById(R.id.etServicioGourmet);
        etNumeroSalas = findViewById(R.id.etNumeroSalas);
        etServicioPersonalizado = findViewById(R.id.etServicioPersonalizado);
    }

    private void setupSpinners()
    {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.opciones_si_no,
                android.R.layout.simple_spinner_dropdown_item);
        spinnerAccesoLounge.setAdapter(adapter);

        spinnerHabitacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Visibility(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void Visibility(String tipoHabitacion)
    {
        etCostoExtraDesayuno.setVisibility(View.GONE);
        spinnerAccesoLounge.setVisibility(View.GONE);
        etServicioGourmet.setVisibility(View.GONE);
        etNumeroSalas.setVisibility(View.GONE);
        etServicioPersonalizado.setVisibility(View.GONE);

        switch (tipoHabitacion)
        {
            case "Estandar":
                etCostoExtraDesayuno.setVisibility(View.VISIBLE);
                break;
            case "Deluxe":
                spinnerAccesoLounge.setVisibility(View.VISIBLE);
                etServicioGourmet.setVisibility(View.VISIBLE);
                break;
            case "Suite":
                etNumeroSalas.setVisibility(View.VISIBLE);
                etServicioPersonalizado.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void setupButtonListeners()
    {
        Button btnAgregarReserva = findViewById(R.id.btnAgregarReserva);
        btnAgregarReserva.setOnClickListener(v -> agregarReserva());
    }

    private void agregarReserva()
    {
        String nombre = etNombreHuesped.getText().toString();
        String fecha = etFechaIngreso.getText().toString();
        int noches = Integer.parseInt(etNumeroNoches.getText().toString());
        double tarifa = Double.parseDouble(etTarifaBase.getText().toString());
        String tipoHabitacion = spinnerHabitacion.getSelectedItem().toString();

        switch (tipoHabitacion)
        {
            case "Estandar":
                double costoDesayuno = Double.parseDouble(etCostoExtraDesayuno.getText().toString());
                controllerHotel.reservas.add(new HabitacionEstandar(nombre, fecha, noches, tarifa, tipoHabitacion, costoDesayuno));
                break;
            case "Deluxe":
                boolean accesoLounge = spinnerAccesoLounge.getSelectedItem().toString().equals("Sí");
                double servicioGourmet = Double.parseDouble(etServicioGourmet.getText().toString());
                controllerHotel.reservas.add(new HabitacionDeluxe(nombre, fecha, noches, tarifa, tipoHabitacion, accesoLounge, servicioGourmet));
                break;
            case "Suite":
                int numeroSalas = Integer.parseInt(etNumeroSalas.getText().toString());
                double servicioPersonalizado = Double.parseDouble(etServicioPersonalizado.getText().toString());
                controllerHotel.reservas.add(new Suite(nombre, fecha, noches, tarifa, tipoHabitacion, numeroSalas, servicioPersonalizado));
                break;
        }

        limpiarCampos();
    }

    private void limpiarCampos()
    {
        etNombreHuesped.setText("");
        etFechaIngreso.setText("");
        etNumeroNoches.setText("");
        etTarifaBase.setText("");
        etCostoExtraDesayuno.setText("");
        etServicioGourmet.setText("");
        etNumeroSalas.setText("");
        etServicioPersonalizado.setText("");
    }
}