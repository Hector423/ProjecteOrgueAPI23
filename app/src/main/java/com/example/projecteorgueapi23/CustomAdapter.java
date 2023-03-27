package com.example.projecteorgueapi23;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

public class CustomAdapter extends RecyclerView.Adapter<CustomVH>{
    private ArrayList<String> id = new ArrayList<>();
    private ArrayList<String> title = new ArrayList<>();
    private ArrayList<Boolean> active = new ArrayList<>();
    private Context context;
    private Musica musica;
    MainActivity main = new MainActivity();

    public CustomAdapter(Context context, ArrayList<String> id, ArrayList<String> title, ArrayList<Boolean> active) {
        this.context = context;
        this.id = id;
        this.title = title;
        this.active = active;
    }

    @NonNull
    @Override
    public CustomVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_cancion, parent, false);
        return new CustomVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomVH holder, int position) {
        holder.title.setText(title.get(position));
        holder.imgPlay.setOnClickListener( v -> {
            Constants.setMusica(id.get(position));
            active.set(position, true);
            Constants.setFiltroBotonClicado(true);

            try {
                InputStream input = context.getAssets().open("canciones.xml");
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
                            main.changeMusic(context);
                            if(musica.isUnMutedGeneral()) {
                                if(Constants.getFiltroBotonClicado()) {
                                    musica.playAudio(context);
                                    Constants.setFiltroBotonClicado(false);
                                }else{
                                    musica.resumeAudio();
                                }
                            }else{
                                musica.pausaAudio();
                            }
                            if (Constants.getMusica().equals(elm.getNodeName())) {
                                if("false".equals(elm.getNodeName())) {
                                    elm.setTextContent("true");
                                }
                            }
                            if (!Constants.getMusica().equals(elm.getNodeName())) {
                                elm.setTextContent("false");
                            }

                            Transformer tf = TransformerFactory.newInstance().newTransformer();

                            DOMSource domSource = new DOMSource(document);
                            File path = context.getFilesDir();
                            Log.i("", ""+path);
                            File itemFile = new File(path,"canciones.xml");
                            StreamResult sr = new StreamResult(itemFile.toURI().getPath());
                            tf.transform(domSource, sr);
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
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

}

class CustomVH extends RecyclerView.ViewHolder{
    TextView title;
    ImageView imgPlay;
    private CustomAdapter adapter;

    public CustomVH(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.cancion);
        imgPlay = itemView.findViewById(R.id.buttonPlay);
    }

    public CustomVH linkAdapter(CustomAdapter adapter) {
        this.adapter = adapter;
        return this;
    }
}

