package com.example.projecteorgueapi23;

public class Constants {
    private static String musica;

    private static Boolean filtroBotonClicado = false;

    public static String getMusica() {
        return musica;
    }

    public static void setMusica(String musica) {
        Constants.musica = musica;
    }

    public static Boolean getFiltroBotonClicado() {
        return filtroBotonClicado;
    }

    public static void setFiltroBotonClicado(Boolean filtroBotonClicado) {
        Constants.filtroBotonClicado = filtroBotonClicado;
    }
}
