package com.example.projecteorgueapi23;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class PreguntaClickImagenes extends AppCompatActivity {
    /*
           Declaramos las variables
            */
    private TextView pregunta;
    private ImageButton imagen1, imagen2, imagen3, imagen4, imagen5, imagen6;
    private ImageView imagenPregunta;
    private Button botoComprovar, botoContinuar;
    private Drawable drawable1, drawable2, drawable3, drawable4, drawable5, drawable6;
    private boolean correcto = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta_imatges);
/*
        Declaramos variables del layout con las de la clase
         */
        imagen1 = findViewById(R.id.imageButton10);
        imagen2 = findViewById(R.id.imageButton11);
        imagen3 = findViewById(R.id.imageButton12);
        imagen4 = findViewById(R.id.imageButton13);
        imagen5 = findViewById(R.id.imageButton14);
        imagen6 = findViewById(R.id.imageButton15);
        imagenPregunta = findViewById(R.id.imageView2);

        botoComprovar = findViewById(R.id.botoComprovarPlanol);
        botoContinuar = findViewById(R.id.botoContinuarPlanol);

        pregunta = findViewById(R.id.textPreguntaPlanol);

        Drawable drawable1 = ResourcesCompat.getDrawable(getResources(), R.drawable.fila_1_columna_1, null);
        Drawable drawable2 = ResourcesCompat.getDrawable(getResources(), R.drawable.fila_1_columna_2, null);
        Drawable drawable3 = ResourcesCompat.getDrawable(getResources(), R.drawable.fila_2_columna_1, null);
        Drawable drawable4 = ResourcesCompat.getDrawable(getResources(), R.drawable.fila_2_columna_2, null);
        Drawable drawable5 = ResourcesCompat.getDrawable(getResources(), R.drawable.fila_3_columna_1, null);
        Drawable drawable6 = ResourcesCompat.getDrawable(getResources(), R.drawable.fila_3_columna_2, null);



 /*
        Empezamos a leer el xml con las preguntas
         */
        try {
            InputStream input = getAssets().open("preguntas.xml");
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(input);
            NodeList nList = doc.getElementsByTagName("pregunta");
            if (nList.item(0).getNodeType() == Node.ELEMENT_NODE) {
                correcto = false;
                Element elm = (Element) nList.item(GlobalVariables.cont);
                String string = getNodeValue("preg", elm);
                pregunta.setText(string);
                Drawable[] drawables = {drawable1, drawable2, drawable3, drawable4, drawable5, drawable6};
                ImageButton[] imageButtons = {imagen1, imagen2, imagen3, imagen4, imagen5, imagen6};



                /*
                Comprobamos si es la pregunta correspondiente y añadimos las imagenes del plano,
                también indicamos que boton es el correcto

                 */
                if(elm.equals(nList.item(0))) {
                    for(int i = 0; i < imageButtons.length; i++){
                        imageButtons[i].setImageDrawable(drawables[i]);
                    }
                    Drawable drawablePregunta = ResourcesCompat.getDrawable(getResources(), R.drawable.escut, null);

                    imagenPregunta.setImageDrawable(drawablePregunta);
                    imagen5.setOnClickListener(v -> {
                        correcto = true;
                    });

                }
                if(elm.equals(nList.item(1))){
                    finish();
                    Intent intent = new Intent(this, PreguntasRespuestas.class);
                    startActivity(intent);
                }
                if(elm.equals(nList.item(3))){
                    finish();
                    Intent intent = new Intent(this, PreguntasRespuestas.class);
                    startActivity(intent);
                }

                if(elm.equals(nList.item(2))){
                    for(int i = 0; i < imageButtons.length; i++){
                        imageButtons[i].setImageDrawable(drawables[i]);
                    }
                    Drawable drawablePregunta = ResourcesCompat.getDrawable(getResources(), R.drawable.orgue_antic07, null);

                    imagenPregunta.setImageDrawable(drawablePregunta);
                    imagen4.setOnClickListener(v -> {
                        correcto = true;
                    });
                }

                /*
                Se comprueba que se ha seleccionado el boton correcto y se habilita el boton de continuar
                 */
                botoComprovar.setOnClickListener(v -> {
                    if(correcto) {
                        botoContinuar.setClickable(true);
                        botoContinuar.setEnabled(true);
                        for(int i = 0; i < imageButtons.length; i++){
                            imageButtons[i].setEnabled(false);
                        }
                        GlobalVariables.puntuacion++;
                    }else{
                        Toast toast = Toast.makeText(this, "Resposta incorrecta. Prova un altre cop!", Toast.LENGTH_LONG);
                        toast.show();
                        GlobalVariables.puntuacion--;
                        GlobalVariables.fallos++;
                    }
                });

                botoContinuar.setOnClickListener(view -> {
                    siguienePregunta();
                });


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


    public void siguienePregunta(){
        finish();
        startActivity(getIntent());
        GlobalVariables.cont++;
    }

}