package com.example.projecteorgueapi23;

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

        botoA.setOnClickListener(v -> grupA());
        botoB.setOnClickListener(v -> grupB());
        botoC.setOnClickListener(v -> grupC());
        botoD.setOnClickListener(v -> grupD());

        }
    private void grupA(){

    }

    private void grupB(){

    }

    private void grupC(){

    }

    private void grupD(){


    }

}
