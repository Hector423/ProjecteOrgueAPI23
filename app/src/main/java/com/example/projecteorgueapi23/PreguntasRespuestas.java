package com.example.projecteorgueapi23;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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

    /**
     * Declarem variables
     */
    private TextView textPregunta;
    private Button botonRespuesta, btnComprova;
    private ImageView imagen;
    private RadioGroup rgp;
    private RadioButton radioButton;
    private int contRadio = 0;
    private boolean comprovado = false;

    private Musica musica = new Musica();

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pregunta);

        /**
         * Assignem les variables al layout
         */
        textPregunta = findViewById(R.id.textPregunta);
        imagen = findViewById(R.id.imatgePregunta);
        rgp = findViewById(R.id.caixaRespostes);
        botonRespuesta = findViewById(R.id.botonRespuesta);
        btnComprova = findViewById(R.id.btnComprovaRes);

        try {
            // Leemos XML con DOM
            InputStream input = getAssets().open("preguntas.xml");
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(input);
            NodeList nList = doc.getElementsByTagName("pregunta");

            // Comprobamos que pregunta nos ha salido y dependiendo de cual ponemos sus respectivas preguntas
            if(nList.item(0).getNodeType() == Node.ELEMENT_NODE){
                Element elm = (Element)nList.item(GlobalVariables.cont);
                String pregunta = getNodeValue("preg", elm);
                textPregunta.setText(pregunta);

                contRadio = elm.getElementsByTagName("respuesta").getLength();
                for(int i = 0; i<contRadio;i++) {
                    radioButton = new RadioButton(this);
                    radioButton.setId(i);
                    radioButton.setText(elm.getElementsByTagName("respuesta").item(i).getTextContent());
                    radioButton.setTextSize(20);
                    rgp.addView(radioButton);
                }

                // Se commprueba si las respuestas son correctas
                if(elm.equals(nList.item(1))){
                    Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.escut, null);
                    imagen.setImageDrawable(drawable);
                    btnComprova.setOnClickListener(view -> {
                        musicaBoton(this);
                        int id = rgp.getCheckedRadioButtonId();
                        if(id == 2){
                            for (int i = 0; i < rgp.getChildCount(); i++) {
                                rgp.getChildAt(i).setEnabled(false);
                            }

                            botonRespuesta.setEnabled(true);
                            comprovado = true;
                            GlobalVariables.puntuacion++;
                        }else{
                            Toast toast = Toast.makeText(this, "Resposta incorrecta. Prova un altre cop!", Toast.LENGTH_LONG);
                            toast.show();
                            GlobalVariables.puntuacion--;
                            GlobalVariables.fallos++;
                        }
                    });
                }else if(elm.equals(nList.item(3))){
                    Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.orgue_antic07, null);
                    imagen.setImageDrawable(drawable);
                    btnComprova.setOnClickListener(view -> {
                        musicaBoton(this);
                        int id = rgp.getCheckedRadioButtonId();
                        if(id == 1){
                            for (int i = 0; i < rgp.getChildCount(); i++) {
                                rgp.getChildAt(i).setEnabled(false);
                            }
                            botonRespuesta.setEnabled(true);
                            comprovado = true;
                            GlobalVariables.puntuacion++;
                        }else{
                            Toast toast = Toast.makeText(this, "Resposta incorrecta. Prova un altre cop!", Toast.LENGTH_LONG);
                            toast.show();
                            GlobalVariables.puntuacion--;
                            GlobalVariables.fallos++;
                        }
                    });
                }else if(elm.equals(nList.item(4))){
                    Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.nou_orgue, null);
                    imagen.setImageDrawable(drawable);
                    btnComprova.setOnClickListener(view -> {
                        musicaBoton(this);
                        int id = rgp.getCheckedRadioButtonId();
                        if(id == 1){
                            for (int i = 0; i < rgp.getChildCount(); i++) {
                                rgp.getChildAt(i).setEnabled(false);
                            }
                            botonRespuesta.setEnabled(true);
                            comprovado = true;
                            GlobalVariables.puntuacion++;
                        }else{
                            Toast toast = Toast.makeText(this, "Resposta incorrecta. Prova un altre cop!", Toast.LENGTH_LONG);
                            toast.show();
                            GlobalVariables.puntuacion--;
                            GlobalVariables.fallos++;
                        }
                    });
                }else if(elm.equals(nList.item(8))){
                    btnComprova.setOnClickListener(view -> {
                        musicaBoton(this);
                        int id = rgp.getCheckedRadioButtonId();
                        if(id == 1){
                            for (int i = 0; i < rgp.getChildCount(); i++) {
                                rgp.getChildAt(i).setEnabled(false);
                            }
                            botonRespuesta.setEnabled(true);
                            comprovado = true;
                            GlobalVariables.puntuacion++;
                        }else{
                            Toast toast = Toast.makeText(this, "Resposta incorrecta. Prova un altre cop!", Toast.LENGTH_LONG);
                            toast.show();
                            GlobalVariables.puntuacion--;
                            GlobalVariables.fallos++;
                        }
                    });
                }
                if(elm.equals(nList.item(2))){
                    finish();
                    Intent intent = new Intent(this, PreguntaClickImagenes.class);
                    startActivity(intent);
                }
                if(elm.equals(nList.item(5))){
                    finish();
                    Intent intent = new Intent(this, PantallarConfirmarUbicacio.class);
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

    // Pasamos a la siguiente pregunta
    public void siguienePregunta(View v){
        musicaBoton(this);
        if(comprovado == true){
            if(GlobalVariables.cont == 8){
                GlobalVariables.cont = 0;
                finish();
                Intent intent = new Intent(this, PantallaFinal.class);
                startActivity(intent);
            }else{
                finish();
                startActivity(getIntent());
                GlobalVariables.cont++;
            }
        }else {

        }

    }

    public void musicaBoton(Context context){
        if(musica.isUnMutedGeneral()) {
            musica.soundButton(context);
        }
    }
}