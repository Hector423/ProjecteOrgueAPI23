package com.example.projecteorgueapi23;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

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

public class PantallarConfirmarUbicacio extends AppCompatActivity {
    /*
        Declaramos las variables
         */
    private Button botoConfirmar;
    private ImageView imagen1, imagen2, imagen3, imagen4, imagen5, imagen6;
    private Drawable drawable1, drawable2, drawable3, drawable4, drawable5, drawable6;
    private TextView pregunta;

    private Musica musica = new Musica();

    /*
        Hacer que cuente como pregunta al continuar
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_ir_a_sitio);

/*
        Declaramos variables del layout con las de la clase
         */
        imagen1 = findViewById(R.id.imageView3);
        imagen2 = findViewById(R.id.imageView4);
        imagen3 = findViewById(R.id.imageView5);
        imagen4 = findViewById(R.id.imageView6);
        imagen5 = findViewById(R.id.imageView7);
        imagen6 = findViewById(R.id.imageView8);

        pregunta = findViewById(R.id.textView8);

        botoConfirmar = findViewById(R.id.botoConfirmar);
        Drawable drawable1 = ResourcesCompat.getDrawable(getResources(), R.drawable.fila_1_columna_1, null);
        Drawable drawable2 = ResourcesCompat.getDrawable(getResources(), R.drawable.fila_1_columna_2, null);
        Drawable drawable3 = ResourcesCompat.getDrawable(getResources(), R.drawable.fila_2_columna_1, null);
        Drawable drawable4 = ResourcesCompat.getDrawable(getResources(), R.drawable.fila_2_columna_2, null);
        Drawable drawable5 = ResourcesCompat.getDrawable(getResources(), R.drawable.fila_3_columna_1, null);
        Drawable drawable6 = ResourcesCompat.getDrawable(getResources(), R.drawable.fila_3_columna_2_punto, null);

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
                Element elm = (Element) nList.item(GlobalVariables.cont);
                String string = getNodeValue("preg", elm);
                pregunta.setText(string);
                Drawable[] drawables = {drawable1, drawable2, drawable3, drawable4, drawable5, drawable6};
                ImageView[] imageViews = {imagen1, imagen2, imagen3, imagen4, imagen5, imagen6};

                /*
                Añadimos las imagenes del plano para que sirva de mapa
                 */
                if(elm.equals(nList.item(5))) {
                    for(int i = 0; i < imageViews.length; i++){
                        imageViews[i].setImageDrawable(drawables[i]);
                    }

                }


            }
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
/*
El boton ejecutara el metodo para ir a la siguiente pregunta
 */
    botoConfirmar.setOnClickListener( v ->
    {
        openSeguentPantalla();
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
    public void openSeguentPantalla(){
        if(musica.isUnMutedGeneral()) {
            musica.soundButton(PantallarConfirmarUbicacio.this);
        }
        GlobalVariables.cont++;
        Intent intent = new Intent(this, PreguntasRelacionar.class);
        startActivity(intent);
    }

}
