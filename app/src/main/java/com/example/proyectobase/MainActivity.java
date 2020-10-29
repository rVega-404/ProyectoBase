package com.example.proyectobase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private ProgressBar pgrBar;
    private EditText eTxtUsuario, eTxtPass;
    private Button btnIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pgrBar = (ProgressBar)findViewById(R.id.pgrBar);
        pgrBar.setVisibility(View.INVISIBLE);

        eTxtUsuario = (EditText)findViewById(R.id.eTxtUsuario);
        eTxtPass = (EditText)findViewById(R.id.eTxtPass);

        btnIniciar = (Button)findViewById(R.id.btnIniciar);
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task().execute();
            }
        });
    }

    class Task extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            pgrBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            for(int i = 1; i <= 5; i++) {
                try {
                    Thread.sleep(1000);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            pgrBar.setVisibility(View.INVISIBLE);
            Intent intent = new Intent(getBaseContext(), Menu_act.class);
            startActivity(intent);
        }
    }
}