package com.example.projecteorgueapi23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class PantallaInfo extends AppCompatActivity {

    private Button botoTornar;
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
        Intent intent = new Intent(this, PantallaInici.class);
        startActivity(intent);
    }
}