package com.example.projecteorgueapi23;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class PreguntasRespuestas extends AppCompatActivity {

    private TextView textPregunta;
    private Button botonRespuesta, btnComprova;
    private ImageView imagen;
    private RadioGroup rgp;
    private RadioButton radioButton;
    private int contRadio = 0;
    private boolean comprovado = false;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pregunta);

        textPregunta = findViewById(R.id.textPregunta);
        imagen = findViewById(R.id.imatgePregunta);
        rgp = findViewById(R.id.caixaRespostes);
        botonRespuesta = findViewById(R.id.botonRespuesta);
        btnComprova = findViewById(R.id.btnComprovaRes);

        try {
            InputStream input = getAssets().open("preguntas.xml");
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(input);
            NodeList nList = doc.getElementsByTagName("pregunta");

            if(nList.item(0).getNodeType() == Node.ELEMENT_NODE){
                Element elm = (Element)nList.item(GlobalVariables.cont);
                String pregunta = getNodeValue("preg", elm);
                textPregunta.setText(pregunta);

                contRadio = elm.getElementsByTagName("respuesta").getLength();

                if(elm.equals(nList.item(0))){
                    for(int i = 0; i<contRadio;i++) {
                        radioButton = new RadioButton(this);
                        radioButton.setId(i);
                        radioButton.setText(elm.getElementsByTagName("respuesta").item(i).getTextContent());
                        rgp.addView(radioButton);
                    }
                    btnComprova.setOnClickListener(view -> {
                        int id = rgp.getCheckedRadioButtonId();
                        if(id == 2){
                            for (int i = 0; i < rgp.getChildCount(); i++) {
                                rgp.getChildAt(i).setEnabled(false);
                            }
                            botonRespuesta.setEnabled(true);
                            comprovado = true;
                        }
                    });
                }else if(elm.equals(nList.item(1))){
                    for(int i = 0; i<contRadio;i++) {
                        radioButton = new RadioButton(this);
                        radioButton.setId(i);
                        radioButton.setText(elm.getElementsByTagName("respuesta").item(i).getTextContent());
                        rgp.addView(radioButton);
                    }
                    btnComprova.setOnClickListener(view -> {
                        int id = rgp.getCheckedRadioButtonId();
                        if(id == 1){
                            for (int i = 0; i < rgp.getChildCount(); i++) {
                                rgp.getChildAt(i).setEnabled(false);
                            }
                            botonRespuesta.setEnabled(true);
                            comprovado = true;
                        }
                    });
                }else if(elm.equals(nList.item(2))){
                    for(int i = 0; i<contRadio;i++) {
                        radioButton = new RadioButton(this);
                        radioButton.setId(i);
                        radioButton.setText(elm.getElementsByTagName("respuesta").item(i).getTextContent());
                        rgp.addView(radioButton);
                    }
                    btnComprova.setOnClickListener(view -> {
                        int id = rgp.getCheckedRadioButtonId();
                        if(id == 1){
                            for (int i = 0; i < rgp.getChildCount(); i++) {
                                rgp.getChildAt(i).setEnabled(false);
                            }
                            botonRespuesta.setEnabled(true);
                            comprovado = true;
                        }
                    });
                }

                if(elm.equals(nList.item(5))){
                    finish();
                    siguienePreguntaIntent();
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
    protected String getNodeValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag);
        Node node = nodeList.item(0);
        if(node!=null){
            if(node.hasChildNodes()){
                Node child = node.getFirstChild();
                while (child!=null){
                    if(child.getNodeType() == Node.TEXT_NODE){
                        return  child.getNodeValue();
                    }
                }
            }
        }
        return "";
    }

    public void siguienePregunta(View v){
        if(comprovado == true){
            finish();
            startActivity(getIntent());
            GlobalVariables.cont++;
        }else{

        }

    }

    public void siguienePreguntaIntent() {
        finish();
        Intent intent = new Intent(this, PreguntasRelacionar.class);
        startActivity(intent);
    }
}