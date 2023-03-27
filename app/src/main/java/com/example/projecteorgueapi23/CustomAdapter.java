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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

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

