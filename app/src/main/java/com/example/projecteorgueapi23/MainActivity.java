package com.example.projecteorgueapi23;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {

    private Button iniciarPreguntes;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarPreguntes = findViewById(R.id.botoInici);
        imageButton = findViewById(R.id.imageButton);
        iniciarPreguntes.setOnClickListener(v ->
        {
            openPreguntes();
        });

//        iniciarPreguntes.setOnClickListener(v -> openPreguntes());
//
//        try {
//            InputStream input = getAssets().open("preguntas.xml");
//            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
//            Document doc = docBuilder.parse(input);
//            NodeList nList = doc.getElementsByTagName("pregunta");
//            for(int i =0;i<nList.getLength();i++){
//                if(nList.item(0).getNodeType() == Node.ELEMENT_NODE){
//                    Element elm = (Element)nList.item(i);
//                    String string = getNodeValue("respuesta", elm);
//                    int id = getResources().getIdentifier(string, "drawable", getPackageName());
//                    Resources res = getResources();
//                    Drawable drawable = ResourcesCompat.getDrawable(res, id, null);
//                    imageButton.setBackground(drawable);;
//                }
//            }
//
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        }


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



    public void openPreguntes(){
        Intent intent = new Intent(this, PreguntasRespuestas.class);
        startActivity(intent);
    }
}