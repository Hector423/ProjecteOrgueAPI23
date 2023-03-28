package com.example.projecteorgueapi23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class PantallaFinal extends AppCompatActivity {

    private TextView encerts, errors, nom;
    private Button botonInici, botonSalir;
    private Musica musica = new Musica();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_final);

        nom = findViewById(R.id.nomJugador);
        encerts = findViewById(R.id.numEncerts);
        errors = findViewById(R.id.numErrors);
        int num = GlobalVariables.puntuacion;
        int fallos = GlobalVariables.fallos;
        nom.setText(GlobalVariables.nombre);
        encerts.setText(String.valueOf(num));
        errors.setText(String.valueOf(fallos));

        botonInici = findViewById(R.id.botoTornarAlMain);
        botonSalir = findViewById(R.id.botoSortirApp);


        botonInici.setOnClickListener( v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            GlobalVariables.nombre = "";
            GlobalVariables.puntuacion = 0;
            GlobalVariables.fallos = 0;
            GlobalVariables.cont = 0;
        });

        botonSalir.setOnClickListener( v  -> {
            musica.pausaAudio();
            finishAffinity();
            GlobalVariables.nombre = "";
            GlobalVariables.puntuacion = 0;
            GlobalVariables.fallos = 0;
            GlobalVariables.cont = 0;
        });
    }
}