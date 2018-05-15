package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.Preferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller.PreferenceAdapter;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.Preference;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;

import java.util.ArrayList;

public class PreferenceActivity extends AppCompatActivity {

    private ListView preference1List, preference2List;
    public PreferenceAdapter directoryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar_pref);


        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle(R.string.preference);


//        if (savedInstanceState == null) {
//            Fragment preferenceFragment = new PreferenceFragmentCustom();
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.replace(R.id.cont_preferences, preferenceFragment);
//            ft.commit();
//        }


        preference1List = findViewById(R.id.list_preference);
        preference2List = findViewById(R.id.list_preference_2);



        loadPreference();

    }


    private void loadPreference() {

        ArrayList<Preference> arrayOfUsers = new ArrayList<>();
        arrayOfUsers.add(new Preference("Pico y Placa","Configurar notificaciones de pico y placa","null","check"));
        arrayOfUsers.add(new Preference("Clima","Recibir notificaciones del clima todas la mañanas","null","check"));
        arrayOfUsers.add(new Preference("Tips de Cultura vial","Frecias de notificaciones a la semana","null","null"));
        //arrayOfUsers.add(new Preference("","","",""));
        PreferenceAdapter adapter = new PreferenceAdapter(this, arrayOfUsers);
        preference1List.setAdapter(adapter);

        arrayOfUsers = new ArrayList<>();
        arrayOfUsers.add(new Preference("Ubicación","Cambiar la ubicación","null","null"));
        adapter = new PreferenceAdapter(this, arrayOfUsers);
        preference2List.setAdapter(adapter);

    }

}
