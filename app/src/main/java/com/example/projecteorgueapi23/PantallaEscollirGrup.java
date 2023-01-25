package com.example.projecteorgueapi23;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PantallaEscollirGrup extends AppCompatActivity {


    private Button botoA, botoB, botoC, botoD;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_grupos);

        botoA = findViewById(R.id.botoGrupA);
        botoB = findViewById(R.id.botoGrupB);
        botoC = findViewById(R.id.botoGrupC);
        botoD = findViewById(R.id.botoGrupD);

        botoA.setOnClickListener(v -> {
            openPantallaInici();
        });
        botoB.setOnClickListener(v -> {
            openPantallaInici();
        });
        botoC.setOnClickListener(v -> {
            openPantallaInici();
        });
        botoD.setOnClickListener(v -> {
            openPantallaInici();
        });


        }

    public void openPantallaInici() {
        Intent intent = new Intent(this, PantallaInici.class);
        startActivity(intent);
    }
}
