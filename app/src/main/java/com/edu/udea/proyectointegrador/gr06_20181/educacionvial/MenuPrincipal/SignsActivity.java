package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.MenuPrincipal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;

public class SignsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signs);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarpreferences);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
