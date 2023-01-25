package com.example.projecteorgueapi23;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PantallarConfirmarUbicacio extends AppCompatActivity {

    private Button botoConfirmar;

    /*
        Hacer que cuente como pregunta al continuar
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_ir_a_sitio);

        botoConfirmar = findViewById(R.id.botoConfirmar);

    botoConfirmar.setOnClickListener( v ->
    {
        openSeguentPantalla();
    });




    }
    public void openSeguentPantalla(){
        Intent intent = new Intent(this, PantallaInici.class);
        startActivity(intent);
    }

}
