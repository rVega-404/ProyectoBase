package com.example.proyectobase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import Classes.AdminSQLiteOpenHelper;

public class Insumos_act extends AppCompatActivity {
    private EditText eTxtCodigo, eTxtNombre, eTxtPrecio, eTxtStock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insumos_act);

        eTxtCodigo = (EditText)findViewById(R.id.eTxtCodigo);
        eTxtNombre= (EditText)findViewById(R.id.eTxtNombre);
        eTxtPrecio = (EditText)findViewById(R.id.eTxtPrecio);
        eTxtStock = (EditText)findViewById(R.id.eTxtStock);
    }

    public void AnadirInsumos(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"fichero", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        if(!eTxtCodigo.getText().toString().isEmpty()) {
            ContentValues cont = new ContentValues();

            cont.put("codigo", eTxtCodigo.getText().toString());
            cont.put("nombre", eTxtNombre.getText().toString());
            cont.put("precio", eTxtPrecio.getText().toString());
            cont.put("stock", eTxtStock.getText().toString());

            db.insert("insumos", null, cont);
            db.close();

            Toast.makeText(this, "Has guardado este insumo", Toast.LENGTH_LONG).show();

            eTxtCodigo.setText("");
            eTxtNombre.setText("");
            eTxtPrecio.setText("");
            eTxtStock.setText("");
        }
        else {
            Toast.makeText(this, "Debe ingresar el código del insumo", Toast.LENGTH_LONG).show();
        }
    }

    public void MostrarInsumos(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "fichero", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String codigo = eTxtCodigo.getText().toString();

        if(!codigo.isEmpty()) {
            // Muestro insumos.
            Cursor fila = db.rawQuery("SELECT nombre, precio, stock FROM insumos WHERE codigo=" + codigo, null); // Obtengo la fila donde codigo = codigo
            if(fila.moveToFirst()) { // Si no hay campos devuelve vacio.
                eTxtNombre.setText(fila.getString(0));
                eTxtPrecio.setText(fila.getString(1));
                eTxtStock.setText(fila.getString(2));
            }
            else {
                Toast.makeText(this, "No hay campos en la tabla", Toast.LENGTH_LONG).show();
            }
        }
        else  {
            Toast.makeText(this, "No hay insumo con el código asociado", Toast.LENGTH_LONG).show();
        }
    }

    public void EliminarInsumos(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "fichero", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String codigo = eTxtCodigo.getText().toString();

        db.delete("insumos", "codigo=" + codigo, null);
        db.close();

        Toast.makeText(this, "Has eliminado un insumo", Toast.LENGTH_LONG).show();
    }

    public void ActualizarInsumos(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "fichero", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String codigo = eTxtCodigo.getText().toString();

        ContentValues cont = new ContentValues();

        cont.put("codigo", eTxtCodigo.getText().toString());
        cont.put("nombre", eTxtNombre.getText().toString());
        cont.put("precio", eTxtPrecio.getText().toString());
        cont.put("stock", eTxtStock.getText().toString());

        if(!codigo.isEmpty()) {
            // Actualizamos.
            db.update("insumos", cont, "codigo=" + codigo, null);
            db.close();
            Toast.makeText(this, "Has actualizado el insumo", Toast.LENGTH_LONG).show();
        }
    }
}