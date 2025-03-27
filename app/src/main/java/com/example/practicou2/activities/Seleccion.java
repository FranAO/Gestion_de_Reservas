package com.example.practicou2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.practicou2.R;
import com.google.android.material.card.MaterialCardView;

public class Seleccion extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion);

        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null)
        {
            setSupportActionBar(toolbar);
        }

        int[] cardIds = {R.id.card1, R.id.card2, R.id.card3};
        for (int id : cardIds)
        {
            View card = findViewById(id);
            if (card != null)
            {
                card.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();
        if (id == R.id.card1)
        {
            Intent intent = new Intent(this, AgregarReserva.class);
            startActivity(intent);
            return;
        } else if (id == R.id.card2)
        {
            Intent intent = new Intent(this, BuscarReserva.class);
            startActivity(intent);
            return;
        } else if (id == R.id.card3)
        {
            Intent intent = new Intent(this, MostrarReserva.class);
            startActivity(intent);
            return;        }
    }
}