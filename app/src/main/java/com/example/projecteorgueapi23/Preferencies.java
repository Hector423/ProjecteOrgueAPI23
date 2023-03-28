package com.example.projecteorgueapi23;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;


public class Preferencies extends PreferenceActivity
{
    private Musica musica = new Musica();
    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencies);

        /**
         * Per les preferencies agafem el checkbox per comprovar si esta marcat o no
         */
        Preference button = findPreference("botonSave");
        button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                boolean prefMusica = pref.getBoolean("musica", true);
                Log.i("Booleano", "" + prefMusica);
                musica.setuNMutedGeneral(prefMusica);
                if(musica.isUnMutedGeneral()) {
                    musica.resumeAudio();
                }else{
                    musica.pausaAudio();
                }
                finish();
                return true;
            }
        });
    }
}