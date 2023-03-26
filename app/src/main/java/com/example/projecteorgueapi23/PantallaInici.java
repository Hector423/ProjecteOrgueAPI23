package com.example.projecteorgueapi23;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PantallaInici extends AppCompatActivity {

    private Button botoInfo, botoIniciarPreguntes;
    private Musica musica = new Musica();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_inici);
        botoInfo = findViewById(R.id.botoInfo);
        botoIniciarPreguntes = findViewById(R.id.botoIniciPreguntes);

        botoInfo.setOnClickListener(v ->
        {
            openPantallaInformació();
        });

        botoIniciarPreguntes.setOnClickListener(v ->
        {
            openPreguntes();
        });
    }

    public void openPantallaInformació(){
        if(musica.isUnMutedGeneral()) {
            musica.soundButton(PantallaInici.this);
        }
        Intent intent = new Intent(this, PantallaInfo.class);
        startActivity(intent);
    }

    public void openPreguntes(){
        if(musica.isUnMutedGeneral()) {
            musica.soundButton(PantallaInici.this);
        }
        Intent intent = new Intent(this, PreguntaClickImagenes.class);
        startActivity(intent);
    }

}