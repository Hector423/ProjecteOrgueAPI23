package com.example.projecteorgueapi23;

import android.content.Context;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;


public class Musica {
    // MediaPlayer variables
    // Declaración de las variables de MediaPlayer
    private static MediaPlayer mp = null;
    private static final float maxVolumen = 0.30f; // Volumen máximo para la música
    private static int cancion = 0;
    private static boolean unMutedGeneral = true; // Indica si el sonido general está activado o no
    private static boolean firstReproduced = false; // Indica si la música se ha reproducido por primera vez o no


    // SoundPool variables
    private static SoundPool sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
    private static int soundId;


    static {
        mp = new MediaPlayer();
    }


    public void getCancion(Integer cancionUrl){
        this.cancion = cancionUrl;
    }

    public static void playAudio(Context context) {
        mp.reset();
        mp = MediaPlayer.create(context, cancion);
        mp.setLooping(true);
        mp.start();
        mp.setVolume(maxVolumen,maxVolumen);
    }

    public static void pausaAudio() {
        if (mp != null && mp.isPlaying()) {
            mp.pause();
        }
    }

    public static void resumeAudio() {
        if (mp != null && !mp.isPlaying()) {
            mp.start();
            mp.setLooping(true);
            mp.setVolume(maxVolumen,maxVolumen);
        }
    }

    public static boolean isUnMutedGeneral() {
        return unMutedGeneral;
    }

    public static void setuNMutedGeneral(boolean muted) {
        Musica.unMutedGeneral = muted;
    }

    public static boolean isFirstReproduced() { return firstReproduced; }

    public static void setFirstReproduced(boolean firstReproduced) { Musica.firstReproduced = firstReproduced; }

    // Reproduce el efecto de sonido del botón
    public static void soundButton(Context context) {
        soundId = sp.load(context, R.raw.boton, 1);
        sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                sp.play(soundId, 1, 1, 0, 0, 1);
            }
        });
    }
}