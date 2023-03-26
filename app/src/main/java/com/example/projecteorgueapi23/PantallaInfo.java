package com.example.projecteorgueapi23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class PantallaInfo extends AppCompatActivity {

    private Button botoTornar;
    private Musica musica = new Musica();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_info);

        botoTornar = findViewById(R.id.botoInici2);

        botoTornar.setOnClickListener(v ->
        {
            openPantallaInici();
        });
    }


    public void openPantallaInici(){
        if(musica.isUnMutedGeneral()) {
            musica.soundButton(PantallaInfo.this);
        }
        Intent intent = new Intent(this, PantallaInici.class);
        startActivity(intent);
    }
}