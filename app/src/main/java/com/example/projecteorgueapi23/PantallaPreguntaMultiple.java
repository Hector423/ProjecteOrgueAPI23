package com.example.projecteorgueapi23;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
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

public class PantallaPreguntaMultiple extends AppCompatActivity {

    /*
    Declaramos las variables
     */
    private TextView pregunta, nombreImagen1, nombreImagen2, nombreImagen3, nombreImagen4, nombreImagen5, nombreImagen6, nombreImagen7, nombreImagen8, nombreImagen9;
    private ImageButton imagen1, imagen2, imagen3, imagen4, imagen5, imagen6, imagen7, imagen8, imagen9;
    private Button botoComprovar, botoContinuar;
    private int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta_multiple);

        /*
        Declaramos variables del layout con las de la clase
         */
        pregunta = findViewById(R.id.textPreguntaImagenes);
        nombreImagen1 = findViewById(R.id.textView28);
        nombreImagen2 = findViewById(R.id.textView29);
        nombreImagen3 = findViewById(R.id.textView30);
        nombreImagen4 = findViewById(R.id.textView31);
        nombreImagen5 = findViewById(R.id.textView32);
        nombreImagen6 = findViewById(R.id.textView33);
        nombreImagen7 = findViewById(R.id.textView34);
        nombreImagen8 = findViewById(R.id.textView35);
        nombreImagen9 = findViewById(R.id.textView36);

        imagen1 = findViewById(R.id.imageButton);
        imagen2 = findViewById(R.id.imageButton2);
        imagen3 = findViewById(R.id.imageButton3);
        imagen4 = findViewById(R.id.imageButton4);
        imagen5 = findViewById(R.id.imageButton5);
        imagen6 = findViewById(R.id.imageButton6);
        imagen7 = findViewById(R.id.imageButton7);
        imagen8 = findViewById(R.id.imageButton8);
        imagen9 = findViewById(R.id.imageButton9);


        botoComprovar = findViewById(R.id.boto);
        botoContinuar = findViewById(R.id.botoContinuar);



        /*
        Empezamos a leer el xml con las preguntas
         */

        try {
            InputStream input = getAssets().open("preguntas.xml");
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(input);
            NodeList nList = doc.getElementsByTagName("pregunta");
            if(nList.item(0).getNodeType() == Node.ELEMENT_NODE){
                Element elm = (Element)nList.item(7);
                String string = getNodeValue("id", elm);
                //Entrara solo si es la pregunta correspondiente
                if(string.equals("6.5")){
                    String textoPregunta = getNodeValue("preg", elm);
                    pregunta.setText(textoPregunta);
                    Resources res = getResources();
                    //Añadimos la imagenes
                    Drawable drawable = ResourcesCompat.getDrawable(res, R.drawable.poms_registres, null);
                    Drawable drawable2 = ResourcesCompat.getDrawable(res, R.drawable.caixa_expressiu, null);
                    Drawable drawable3 = ResourcesCompat.getDrawable(res, R.drawable.secrets, null);
                    Drawable drawable4 = ResourcesCompat.getDrawable(res, R.drawable.pedaler, null);
                    Drawable drawable5 = ResourcesCompat.getDrawable(res, R.drawable.pedal_expressio, null);
                    Drawable drawable6 = ResourcesCompat.getDrawable(res, R.drawable.barnilles, null);
                    Drawable drawable7 = ResourcesCompat.getDrawable(res, R.drawable.tubs , null);
                    Drawable drawable8 = ResourcesCompat.getDrawable(res, R.drawable.manxa_orgue01, null);
                    Drawable drawable9 = ResourcesCompat.getDrawable(res, R.drawable.manuals, null);

                    imagen1.setImageDrawable(drawable);
                    imagen2.setImageDrawable(drawable2);
                    imagen3.setImageDrawable(drawable3);
                    imagen4.setImageDrawable(drawable4);
                    imagen5.setImageDrawable(drawable5);
                    imagen6.setImageDrawable(drawable6);
                    imagen7.setImageDrawable(drawable7);
                    imagen8.setImageDrawable(drawable8);
                    imagen9.setImageDrawable(drawable9);

                    //Añadimos los nombres de cada imagen al textView que tienen debajo
                    TextView[] textViews = {nombreImagen1, nombreImagen2, nombreImagen3,  nombreImagen4, nombreImagen5, nombreImagen6, nombreImagen7, nombreImagen8, nombreImagen9};
                    int contRadio = elm.getElementsByTagName("respuesta").getLength();
                    for(int i =0; i<contRadio; i++){
                        textViews[i].setText(elm.getElementsByTagName("respuesta").item(i).getTextContent());
                    }
                    // siguiente pregunta
                    if(elm.equals(nList.item(5))){
                        finish();
                        siguienePreguntaIntent();
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
        Añadimos en cada imagen el contador de + o - dependiendo de
        si es la respuesta correcta y hacemos que solo se pueda clicar una vez
         */
        imagen1.setOnClickListener(v -> {
            cont++;
            imagen1.setEnabled(false);
        });
        imagen2.setOnClickListener(v -> {
            cont--;
            imagen2.setEnabled(false);
        });
        imagen3.setOnClickListener(v -> {
            cont--;
            imagen3.setEnabled(false);
        });
        imagen4.setOnClickListener(v -> {
            cont++;
            imagen4.setEnabled(false);
        });
        imagen5.setOnClickListener(v -> {
            cont++;
            imagen5.setEnabled(false);
        });
        imagen6.setOnClickListener(v -> {
            cont--;
            imagen6.setEnabled(false);
        });
        imagen7.setOnClickListener(v -> {
            cont++;
            imagen7.setEnabled(false);
        });
        imagen8.setOnClickListener(v -> {
            cont--;
            imagen8.setEnabled(false);
        });
        imagen9.setOnClickListener(v -> {
            cont++;
            imagen9.setEnabled(false);
        });

        /*
        Al pulsar el boton de comprobar, si se han introducido las respuestas correctas
        se habilita el boton de continuar, sino si resetea el contador global y se habilitan
        los botones otra vez
         */
        botoComprovar.setOnClickListener(v -> {
            if(cont == 5){
                botoContinuar.setClickable(true);
                botoContinuar.setEnabled(true);
            }else{
                cont = 0;
                imagen1.setEnabled(true);
                imagen2.setEnabled(true);
                imagen3.setEnabled(true);
                imagen4.setEnabled(true);
                imagen5.setEnabled(true);
                imagen6.setEnabled(true);
                imagen7.setEnabled(true);
                imagen8.setEnabled(true);
                imagen9.setEnabled(true);
            }
        });


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
        finish();
        startActivity(getIntent());
        GlobalVariables.cont++;
    }

    public void siguienePreguntaIntent(){
        finish();
        Intent intent = new Intent(this, PreguntasRelacionar.class);
        startActivity(intent);
    }
}