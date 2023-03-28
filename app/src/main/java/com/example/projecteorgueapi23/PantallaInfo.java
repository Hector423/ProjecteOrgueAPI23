package com.example.projecteorgueapi23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class PantallaInfo extends AppCompatActivity {
    /**
     * Declarem variables
     */
    private Button botoTornar;
    private Musica musica = new Musica();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_info);
        /**
         * Assignem les variables amb el corresponent al layout i en el cas del boton li posem el setOnClickListener
         */
        botoTornar = findViewById(R.id.botoInici2);

        botoTornar.setOnClickListener(v ->
        {
            openPantallaInici();
        });
    }

    /**
     * Metode que comprova el permís de la música i retorna a la pantalla de inici
     */
    public void openPantallaInici(){
        if(musica.isUnMutedGeneral()) {
            musica.soundButton(PantallaInfo.this);
        }
        Intent intent = new Intent(this, PantallaInici.class);
        startActivity(intent);
    }
}