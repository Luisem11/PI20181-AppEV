package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.MainActivity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.AccidentsFragment;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.HelpFragment;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.TrafficFineFragment;

public class HelpActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarpreferences);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        BottomNavigationView navigationView =  findViewById(R.id.bottom_navigation);
        navigationView.setItemIconTintList(null);
        navigationView.setOnNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction ft;

        switch (item.getItemId()) {
            case R.id.action_accidents:
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.help_container, new AccidentsFragment()).addToBackStack(null);
                ft.commit();
                break;

            case R.id.action_traffic_fines:
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.help_container, new TrafficFineFragment()).addToBackStack(null);
                ft.commit();
                break;

            case R.id.action_help:
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.help_container, new HelpFragment()).addToBackStack(null);
                ft.commit();
                break;
        }
        return true;
    }




}
