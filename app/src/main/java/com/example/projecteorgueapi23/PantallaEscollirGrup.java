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



        }

        public void cambiarVentana(View v){
        PantallaInici pantallaInici = new PantallaInici(this);

        if(v==botoA){
            startActivity(new Intent(this, PantallaInici.class));
        }else if(v==botoB){
            startActivity(new Intent(this, PantallaInici.class));
        }else if(v==botoC){
            startActivity(new Intent(this, PantallaInici.class));
        }else if(v==botoD){
            startActivity(new Intent(this, PantallaInici.class));
        }
    }

}
