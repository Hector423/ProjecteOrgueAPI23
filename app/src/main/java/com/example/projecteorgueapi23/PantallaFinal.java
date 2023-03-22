package com.example.projecteorgueapi23;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PantallaFinal extends AppCompatActivity {

    private TextView encerts, errors;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_final);

        encerts = findViewById(R.id.numEncerts);
        errors = findViewById(R.id.numErrors);
        int num = GlobalVariables.puntuacion;
        int fallos = GlobalVariables.fallos;
        encerts.setText(String.valueOf(num));
        errors.setText(String.valueOf(fallos));




    }






}