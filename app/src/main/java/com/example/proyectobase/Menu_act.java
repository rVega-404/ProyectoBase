package com.example.proyectobase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class Menu_act extends AppCompatActivity {
    private ViewFlipper vf;
    private int[] imagenes = {R.drawable.a, R.drawable.b, R.drawable.c};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_act);

        vf = (ViewFlipper)findViewById(R.id.vf);

        for(int i = 0; i < imagenes.length; i++) {
            FlipImagen(imagenes[i]);
        }
    }

    public void FlipImagen(int i) {
        ImageView view = new ImageView(this);
        view.setBackgroundResource(i);
        vf.addView(view);
        vf.setAutoStart(true);
        vf.setFlipInterval(3000);

        vf.setInAnimation(this, android.R.anim.slide_in_left);
        vf.setOutAnimation(this, android.R.anim.slide_out_right);
    }

    public void Maps(View view) {
        Intent intent = new Intent(this, Maps_act.class);
        startActivity(intent);
    }

    public void Info(View view) {
        Intent intent = new Intent(this,  Info_act.class);
        startActivity(intent);
    }

    public void Insumos(View view) {
        Intent intent = new Intent(this,  Insumos_act.class);
        startActivity(intent);
    }

    public void Clientes(View view) {
        ArrayList<String> clientes = new ArrayList<String>();
        clientes.add("Roberto");
        clientes.add("Paula");
        clientes.add("Wilson");

        ArrayList<String> planes = new ArrayList<String>();
        planes.add("Basic");
        planes.add("Pro");

        Intent intent = new Intent(this, Clientes_act.class);
        intent.putExtra("listaClientes", clientes); // Preparando el dato para ser enviado.
        intent.putExtra("listaPlanes", planes);
        startActivity(intent);
    }
}