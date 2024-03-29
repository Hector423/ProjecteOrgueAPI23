package com.example.projecteorgueapi23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageButton;

import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class MainActivity extends AppCompatActivity {

    /*
    Declarem variables
     */
    private Button iniciarPreguntes, preferencies;
    private ImageView listaMusica;
    private ArrayList<String> id = new ArrayList<>();
    private ArrayList<Boolean> active = new ArrayList<>();
    private EditText nom;

    private Musica musica = new Musica();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        /*
        Assignem les variables amb el layout
         */
        nom = findViewById(R.id.AfegirNom);
        iniciarPreguntes = findViewById(R.id.botoInici);
        listaMusica = findViewById(R.id.botonListado);

        try {
            /*
            Llegim el fitxer canciones.xml per agafar les cançons
             */
            InputStream input = getAssets().open("canciones.xml");
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(input);
            NodeList nList = document.getElementsByTagName("cancion");

            for(int i = 0; i<nList.getLength(); i++){
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    /*
                    Recorrem els nodes del xml
                     */
                    Element elm = (Element) nList.item(i);
                    id.add(elm.getElementsByTagName("id").item(0).getTextContent());
                    active.add(Boolean.valueOf(elm.getElementsByTagName("active").item(0).getTextContent()));
                    if(active.get(i)){
                        int musica_id = this.getResources().getIdentifier(id.get(i), "raw",
                                this.getPackageName());
                        Log.i("", "Cancion " + musica_id);
                        musica.getCancion(musica_id);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        /*
        Comprovem les preferencies per veure si reproduir la música o no
         */
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

        /*
        S'assignen les accions dels botons
         */
        listaMusica.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListadoCanciones.class);
            startActivity(intent);
        });
      
        iniciarPreguntes.setOnClickListener(v -> {
                    GlobalVariables.nombre = nom.getText().toString();
                    openPreguntes();
                });

        preferencies.setOnClickListener(v -> {
            Intent intent = new Intent(this, Preferencies.class);
            startActivity(intent);

        });


    }

    /*
    Aquests son els diferents metodes que es criden en la classe
     */
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

    public void changeMusic(Context context){
        if(Constants.getMusica() != null) {
            int musica_id = context.getResources().getIdentifier(Constants.getMusica(), "raw",
                    context.getPackageName());
            Log.i("", "Cancion " + musica_id);
            musica.getCancion(musica_id);
        }
    }
}