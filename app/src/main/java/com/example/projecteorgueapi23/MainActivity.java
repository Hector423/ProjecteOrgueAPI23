package com.example.projecteorgueapi23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button iniciarPreguntes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarPreguntes = findViewById(R.id.botoInici);

        iniciarPreguntes.setOnClickListener(v -> openPreguntes());
    }

    public void openPreguntes(){
        Intent intent = new Intent(this, PantallaEscollirGrup.class);
        startActivity(intent);
    }
}