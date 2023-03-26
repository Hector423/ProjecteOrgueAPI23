package com.example.projecteorgueapi23;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;


public class Preferencies extends PreferenceActivity
{
    private Musica musica = new Musica();
    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencies);

        Preference button = findPreference("botonSave");
        button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                //code for what you want it to do
                return true;
            }
        });
    }
}
