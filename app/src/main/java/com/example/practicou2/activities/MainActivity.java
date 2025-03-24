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

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.practicou2.R;
import com.example.practicou2.controllers.ControllerHotel;
import com.example.practicou2.models.HabitacionDeluxe;
import com.example.practicou2.models.HabitacionEstandar;
import com.example.practicou2.models.Reserva;
import com.example.practicou2.models.Suite;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public EditText etNombreHuesped;
    public EditText etFechaIngreso;
    public EditText etNumeroNoches;
    public EditText etTarifaBase;
    public EditText etBuscar;
    public EditText etCostoExtraDesayuno;
    public EditText etServicioGourmet;
    public EditText etNumeroSalas;
    public EditText etServicioPersonalizado;
    public TextView tvResultado;
    public Spinner spinnerAccesoLounge; 
    public Spinner spinnerReservas;
    public Spinner spinnerHabitacion;
    public ArrayAdapter<String> spinnerAdapter;
    public ArrayList<String> nombresReservas = new ArrayList<>();
    public ControllerHotel controllerHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        controllerHotel = new ControllerHotel();
        init();
    }

    public void init() {
        spinnerReservas = findViewById(R.id.spinnerReservas);
        spinnerHabitacion = findViewById(R.id.spinnerTipoHabitacion);
        etNombreHuesped = findViewById(R.id.etNombreHuesped);
        etFechaIngreso = findViewById(R.id.etFechaIngreso);
        etNumeroNoches = findViewById(R.id.etNumeroNoches);
        etTarifaBase = findViewById(R.id.etTarifaBase);
        etBuscar = findViewById(R.id.etBuscar);

        etCostoExtraDesayuno = findViewById(R.id.etCostoExtraDesayuno);
        spinnerAccesoLounge = findViewById(R.id.spinnerAccesoLounge);
        etServicioGourmet = findViewById(R.id.etServicioGourmet);
        etNumeroSalas = findViewById(R.id.etNumeroSalas);
        etServicioPersonalizado = findViewById(R.id.etServicioPersonalizado);
        tvResultado = findViewById(R.id.tvResultado);

        Button btnAgregarReserva = findViewById(R.id.btnAgregarReserva);
        Button btnBuscar = findViewById(R.id.btnBuscar);
        Button btnMostrar = findViewById(R.id.btnMostrar);

        ArrayAdapter<CharSequence> adapterAccesoLounge = ArrayAdapter.createFromResource(
                this,
                R.array.opciones_si_no,
                android.R.layout.simple_spinner_item
        );
        adapterAccesoLounge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAccesoLounge.setAdapter(adapterAccesoLounge);

        for (Reserva reserva : controllerHotel.reservas) {
            nombresReservas.add(reserva.getNombreHuesped() + " - " + reserva.getTipoHabitacion());
        }

        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresReservas);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerReservas.setAdapter(spinnerAdapter);
        spinnerHabitacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tipoHabitacion = parent.getItemAtPosition(position).toString();

                etCostoExtraDesayuno.setVisibility(View.INVISIBLE);
                spinnerAccesoLounge.setVisibility(View.INVISIBLE);
                etServicioGourmet.setVisibility(View.INVISIBLE);
                etNumeroSalas.setVisibility(View.INVISIBLE);
                etServicioPersonalizado.setVisibility(View.INVISIBLE);

                switch (tipoHabitacion) {
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

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacer nada
            }
        });

        btnAgregarReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etNombreHuesped.getText().toString();
                String fecha = etFechaIngreso.getText().toString();
                int noches = Integer.parseInt(etNumeroNoches.getText().toString());
                double tarifa = Double.parseDouble(etTarifaBase.getText().toString());
                String tipoHabitacion = spinnerHabitacion.getSelectedItem().toString();

                Reserva nuevaReserva;
                switch (tipoHabitacion) {
                    case "Estandar":
                        double costoextradesayuno = Double.parseDouble(etCostoExtraDesayuno.getText().toString());
                        nuevaReserva = new HabitacionEstandar(nombre, fecha, noches, tarifa, tipoHabitacion, costoextradesayuno);
                        break;
                    case "Deluxe":
                        String seleccionAccesoLounge = spinnerAccesoLounge.getSelectedItem().toString();
                        boolean accesolounge = seleccionAccesoLounge.equals("Sí"); // Convertir a booleano
                        double serviciogourmet = Double.parseDouble(etServicioGourmet.getText().toString());
                        nuevaReserva = new HabitacionDeluxe(nombre, fecha, noches, tarifa, tipoHabitacion, accesolounge, serviciogourmet);
                        break;
                    case "Suite":
                        int numerosalas = Integer.parseInt(etNumeroSalas.getText().toString());
                        double serviciopersonalizado = Double.parseDouble(etServicioPersonalizado.getText().toString());
                        nuevaReserva = new Suite(nombre, fecha, noches, tarifa, tipoHabitacion, numerosalas, serviciopersonalizado);
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "Tipo de habitación no válido", Toast.LENGTH_SHORT).show();
                        return;
                }

                // Agregar la reserva al controlador
                controllerHotel.reservas.add(nuevaReserva);

                // Actualizar el Spinner
                nombresReservas.add(nombre + " - " + tipoHabitacion);
                spinnerAdapter.notifyDataSetChanged();
            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoBusqueda = etBuscar.getText().toString().trim();
                Reserva reservaEncontrada = controllerHotel.buscarReserva(textoBusqueda, textoBusqueda);
                if (reservaEncontrada != null) {
                    tvResultado.setText(reservaEncontrada.mostrarDetalles() + "\nPrecio: " + reservaEncontrada.calcularTotal());
                } else {
                    tvResultado.setText("No se encontró ninguna reserva.");
                }
            }
        });

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder detalles = new StringBuilder();
                for (Reserva reserva : controllerHotel.reservas) {
                    detalles.append(reserva.mostrarDetalles())
                            .append("\nPrecio: ")
                            .append(reserva.calcularTotal())
                            .append("\n\n");
                }
                tvResultado.setText(detalles.toString());
            }
        });
    }

    private Reserva getReservaSeleccionada() {
        int pos = spinnerReservas.getSelectedItemPosition();
        return controllerHotel.reservas.get(pos);
    }
}