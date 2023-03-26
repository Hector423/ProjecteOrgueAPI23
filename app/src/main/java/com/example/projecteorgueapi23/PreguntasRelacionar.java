package com.example.projecteorgueapi23;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;


import android.content.Intent;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class PreguntasRelacionar extends AppCompatActivity {
    private TextView titulo, t1, t2, t3, t4;
    private Button botonRespuesta, btnComprova;
    private ImageView imagen1, imagen2, imagen3, imagen4;
    private Spinner sp1, sp2, sp3, sp4;
    private boolean comprovado = false;
    private int contador = 0, f1 = 0, f2 = 0, f3 = 0, f4 = 0;
    private ArrayAdapter<CharSequence> adaptador;

    private Musica musica = new Musica();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta_relacionar);

        titulo = findViewById(R.id.titleRel);
        t1 = findViewById(R.id.tRel1);
        t2 = findViewById(R.id.tRel2);
        t3 = findViewById(R.id.tRel3);
        t4 = findViewById(R.id.tRel4);
        sp1 = findViewById(R.id.spinner1);
        sp2 = findViewById(R.id.spinner2);
        sp3 = findViewById(R.id.spinner3);
        sp4 = findViewById(R.id.spinner4);

        imagen1 = findViewById(R.id.imageView9);
        imagen2 = findViewById(R.id.imageView10);
        imagen3 = findViewById(R.id.imageView11);
        imagen4 = findViewById(R.id.imageView12);


        adaptador = ArrayAdapter.createFromResource(this, R.array.respuestasArray, android.R.layout.simple_spinner_item);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_item);

        sp1.setAdapter(adaptador);
        sp2.setAdapter(adaptador);
        sp3.setAdapter(adaptador);
        sp4.setAdapter(adaptador);

        botonRespuesta = findViewById(R.id.nextPregRel);
        btnComprova = findViewById(R.id.btnComrpovarRel);
        try {
            // Leemos XML con DOM
            InputStream input = getAssets().open("preguntas.xml");
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(input);
            NodeList nList = doc.getElementsByTagName("pregunta");
            if (nList.item(0).getNodeType() == Node.ELEMENT_NODE) {
                Element elm = (Element) nList.item(GlobalVariables.cont);
                if(elm.equals(nList.item(6))) {
                    // Insertamos en los campos el texto leido del xml
                    String pregunta = getNodeValue("preg", elm);
                    titulo.setText(pregunta);
                    t1.setText(elm.getElementsByTagName("respuestaA1").item(0).getTextContent());
                    t2.setText(elm.getElementsByTagName("respuestaB1").item(0).getTextContent());
                    t3.setText(elm.getElementsByTagName("respuestaC1").item(0).getTextContent());
                    t4.setText(elm.getElementsByTagName("respuestaD1").item(0).getTextContent());

                    Drawable drawable1 = ResourcesCompat.getDrawable(getResources(), R.drawable.orgue_portatiu, null);
                    Drawable drawable2 = ResourcesCompat.getDrawable(getResources(), R.drawable.orgue_positiu, null);
                    Drawable drawable3 = ResourcesCompat.getDrawable(getResources(), R.drawable.orgue_principal, null);
                    Drawable drawable4 = ResourcesCompat.getDrawable(getResources(), R.drawable.gran_orgue, null);

                    imagen1.setImageDrawable(drawable1);
                    imagen2.setImageDrawable(drawable2);
                    imagen3.setImageDrawable(drawable3);
                    imagen4.setImageDrawable(drawable4);
                    // Comprueba si las respuestas son las correctas
                    btnComprova.setOnClickListener(view -> {
                        if(musica.isUnMutedGeneral()) {
                            musica.soundButton(PreguntasRelacionar.this);
                        }
                        if (sp1.getSelectedItem().equals("es porta a sobre mentre el toca") && sp2.getSelectedItem().equals("es pot posar a diferents llocs")
                                && sp3.getSelectedItem().equals("té només un teclat però ja té dimensions considerables") && sp4.getSelectedItem().equals("té milers de tubs i necessita un espai gran per a posar-lo")) {
                            sp1.setEnabled(false);
                            sp2.setEnabled(false);
                            sp3.setEnabled(false);
                            sp4.setEnabled(false);
                            botonRespuesta.setEnabled(true);
                            comprovado = true;
                            GlobalVariables.puntuacion++;
                        }else{
                            GlobalVariables.puntuacion--;
                            GlobalVariables.fallos++;
                        }
                    });
                }

                if (elm.equals(nList.item(7))) {
                    finish();
                    Intent intent = new Intent(this, PantallaPreguntaMultiple.class);
                    startActivity(intent);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }


    }

    // Obtiene el nodo del xml pasado por parametro y devolvemos el hijo
    protected String getNodeValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag);
        Node node = nodeList.item(0);
        if (node != null) {
            if (node.hasChildNodes()) {
                Node child = node.getFirstChild();
                while (child != null) {
                    if (child.getNodeType() == Node.TEXT_NODE) {
                        return child.getNodeValue();
                    }
                }
            }
        }
        return "";
    }

    // Pasamos a la siguiente pregunta
    public void siguienePregunta(View v) {
        if(musica.isUnMutedGeneral()) {
            musica.soundButton(PreguntasRelacionar.this);
        }
        finish();
        startActivity(getIntent());
        GlobalVariables.cont++;
    }
}
