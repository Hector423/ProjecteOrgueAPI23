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
    private static MediaPlayer mp = null;
    private static final float maxVolumen = 0.30f;
    private static boolean muted = true;
    private static boolean unMutedGeneral = true;

    private static boolean firstReproduced = false;

    // SoundPool variables
    private static SoundPool sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
    private static int soundId;


    static {
        mp = new MediaPlayer();
    }

    public static void playAudio(Context context) {
        mp.reset();
        mp = MediaPlayer.create(context, R.raw.fondo);
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
