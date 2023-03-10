package com.example.projecteorgueapi23;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PantallaInici extends AppCompatActivity {

    private Button botoInfo, botoIniciarPreguntes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_inici);
        botoInfo = findViewById(R.id.botoInfo);
        botoIniciarPreguntes = findViewById(R.id.botoIniciPreguntes);

        botoInfo.setOnClickListener(v ->
        {
            openPantallaInformaciĆ³();
        });

        botoIniciarPreguntes.setOnClickListener(v ->
        {
            openPreguntes();
        });
    }

    public void openPantallaInformaciĆ³(){
        Intent intent = new Intent(this, PantallaInfo.class);
        startActivity(intent);
    }

    public void openPreguntes(){
        Intent intent = new Intent(this, PreguntaClickImagenes.class);
        startActivity(intent);
    }

}