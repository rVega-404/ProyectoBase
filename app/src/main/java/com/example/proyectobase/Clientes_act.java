package com.example.proyectobase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Classes.Planes;

public class Clientes_act extends AppCompatActivity {
    private Spinner spnClientes, spnPlanes;
    private EditText eTxtMonto;
    private TextView txtNotificacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_act);

        spnClientes = (Spinner)findViewById(R.id.spnClientes);
        spnPlanes = (Spinner)findViewById(R.id.spnPlanes);
        eTxtMonto = (EditText)findViewById(R.id.eTxtMonto);
        txtNotificacion = (TextView)findViewById(R.id.txtNotificacion);

        ArrayList<String> listaClientes = (ArrayList<String>) getIntent().getSerializableExtra("listaClientes");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaClientes);
        spnClientes.setAdapter(adapter);

        ArrayList<String> listaPlanes = (ArrayList<String>) getIntent().getSerializableExtra("listaPlanes");
        ArrayAdapter<String> adapterPlanes = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaPlanes);
        spnPlanes.setAdapter(adapterPlanes);
    }

    public void Calcular(View view) {
        String cliente = spnClientes.getSelectedItem().toString();
        String plan = spnPlanes.getSelectedItem().toString();

        Planes planes = new Planes();

        try {
            int monto = Integer.parseInt(eTxtMonto.getText().toString());

            int resultadoBasic = monto - planes.getBasic();
            int resultadoPro = monto - planes.getPro();

            if(plan.equals("Basic") && resultadoBasic >= 0) {
                txtNotificacion.setText(cliente + " Su vuelto es: " + resultadoBasic);
            }
            else {
                Toast.makeText(this, "Monto insuficiente", Toast.LENGTH_LONG).show();
            }

            if(plan.equals("Pro") && resultadoPro >= 0) {
                txtNotificacion.setText(cliente + " Su vuelto es: " + resultadoPro);
            }
            else {
                Toast.makeText(this, "Monto insuficiente", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e) {
            Toast.makeText(this, "Ingrese un monto válido", Toast.LENGTH_LONG).show();
        }


    }
}