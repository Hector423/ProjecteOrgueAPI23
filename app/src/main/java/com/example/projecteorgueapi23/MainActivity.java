package com.example.projecteorgueapi23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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

    private Button iniciarPreguntes, preferencies;
    private View nom;

    private ImageView listaMusica;
    private ArrayList<String> id = new ArrayList<>();
    private ArrayList<Boolean> active = new ArrayList<>();
    private Musica musica = new Musica();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nom = findViewById(R.id.AfegirNom);
        iniciarPreguntes = findViewById(R.id.botoInici);
        listaMusica = findViewById(R.id.botonListado);

        try {
            InputStream input = getAssets().open("canciones.xml");
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(input);
            NodeList nList = document.getElementsByTagName("cancion");

            for(int i = 0; i<nList.getLength(); i++){
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
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

        listaMusica.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListadoCanciones.class);
            startActivity(intent);
        });

        iniciarPreguntes.setOnClickListener(v -> {
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

        try {
            InputStream input = getAssets().open("canciones.xml");
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(input);
            NodeList nList = document.getElementsByTagName("cancion");

            for(int i = 0; i<nList.getLength(); i++){
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element elm = (Element) nList.item(i);
                    id.add(elm.getElementsByTagName("id").item(0).getTextContent());
                    active.add(Boolean.valueOf(elm.getElementsByTagName("active").item(0).getTextContent()));
                    if(Constants.getMusica() != null) {
                        int musica_id = this.getResources().getIdentifier(Constants.getMusica(), "raw",
                                this.getPackageName());
                        Log.i("", "Cancion " + musica_id);
                        musica.getCancion(musica_id);

                        if (Constants.getMusica().equals(elm.getNodeName())) {
//                            elm.getElementsByTagName("active").item(0).setTextContent("true");
                            if("false".equals(elm.getNodeName())) {
                                elm.setTextContent("true");
                            }
                        }

                        if (!Constants.getMusica().equals(elm.getNodeName())) {
//                            elm.getElementsByTagName("active").item(0).setTextContent("false");
                            elm.setTextContent("false");
                        }

                        Transformer tf = TransformerFactory.newInstance().newTransformer();

                        DOMSource domSource = new DOMSource(document);
                        File path = getFilesDir();
                        Log.i("", ""+path);
                        File itemFile = new File(path,"canciones.xml");
                        StreamResult sr = new StreamResult(itemFile.toURI().getPath());
                        tf.transform(domSource, sr);


                        if(musica.isUnMutedGeneral()) {
                            if(Constants.getFiltroBotonClicado()) {
                                musica.playAudio(MainActivity.this);
                                Constants.setFiltroBotonClicado(false);
                            }else{
                                musica.resumeAudio();
                            }
                        }else{
                            musica.pausaAudio();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
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