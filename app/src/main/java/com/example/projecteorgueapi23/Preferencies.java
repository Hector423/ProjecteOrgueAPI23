package com.example.projecteorgueapi23;

import android.os.Bundle;
import android.preference.PreferenceActivity;


public class Preferencies extends PreferenceActivity
{
    private Musica musica = new Musica();
    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencies);
    }
}
