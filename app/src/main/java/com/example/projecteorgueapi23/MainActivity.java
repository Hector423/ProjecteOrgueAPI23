package com.example.projecteorgueapi23;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {

    private Button iniciarPreguntes, preferencies;
    private View nom;
    private ImageButton imageButton;
    private Uri uriImage;
    private ImageView imageView;
    private Musica musica = new Musica();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nom = findViewById(R.id.AfegirNom);
        iniciarPreguntes = findViewById(R.id.botoInici);
        preferencies = findViewById(R.id.preferencies);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putBoolean("musica", prefs.getBoolean("musica", true)).commit();
        if(musica.isUnMutedGeneral()) {
            if (!musica.isFirstReproduced()) {
                musica.playAudio(MainActivity.this);
                musica.setFirstReproduced(true);
            } else {
                musica.resumeAudio();
            }
        }else{
            musica.pausaAudio();
        }

        preferenciasMusica();

        iniciarPreguntes.setOnClickListener(v ->
                {
                         openPreguntes();
                });

        preferencies.setOnClickListener(v -> {
            Intent intent = new Intent(this, Preferencies.class);
            startActivity(intent);
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        preferenciasMusica();
        if(musica.isUnMutedGeneral()) {
            musica.resumeAudio();
        }else{
            musica.pausaAudio();
        }
    }

    public void openPreguntes(){
        if(musica.isUnMutedGeneral()) {
            musica.soundButton(MainActivity.this);
        }
        Intent intent = new Intent(this, PantallaInici.class);
        startActivity(intent);
    }

    public void preferenciasMusica(){

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        boolean prefMusica = pref.getBoolean("musica", true);

        musica.setuNMutedGeneral(prefMusica);
    }
}