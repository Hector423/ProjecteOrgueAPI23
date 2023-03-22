package com.example.projecteorgueapi23;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.io.IOException;
import java.util.Random;

public class Musica {
    // MediaPlayer variables
    private static final int cancion = R.raw.fondo;
    private static MediaPlayer mp = null, mpBoton = null;
    private static int audioIndex = 0;
    private static boolean muted = true;
    private static boolean unMutedGeneral = true;

    // SoundPool variables
    private static SoundPool sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
    private static int soundId;


    static {
        mp = new MediaPlayer();
        mp.setLooping(true);
    }

    public static MediaPlayer getMp() {
        return mp;
    }

    public static void playAudio(Context context) {
        try {
            mp.reset();
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void pausaAudio() {
        if (mp != null && mp.isPlaying()) {
            mp.pause();
        }
    }

    public static void resumeAudio() {
        if (mp != null && !mp.isPlaying()) {
            mp.start();
        }
    }

    public static void PausePreferences(boolean boleano){
        if(!boleano){
            mp.pause();
        }else{
            mp.start();
        }
    }

    public static void releaseMediaPlayer() {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    public static boolean isMuted() {
        return muted;
    }

    public static void setMuted(boolean muted) {
        Musica.muted = muted;
    }

    public static boolean isUnMutedGeneral() {
        return unMutedGeneral;
    }

    public static void setuNMutedGeneral(boolean muted) {
        Musica.unMutedGeneral = muted;
    }

    public static void soundButton(Context context) {
        soundId = sp.load(context, R.raw.boton, 1);
        sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                sp.play(soundId, 1, 1, 0, 0, 1);
            }
        });
    }

    public static void stopButton(Context context) {
        soundId = sp.load(context, R.raw.boton, 1);
        sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                sp.stop(1);
            }
        });
    }
}
