package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.MainActivity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.MainActivity.Help.AccidentsFragment;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.MainActivity.Help.HelpFragment;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.MainActivity.Help.TrafficFineFragment;

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
        navigationView.setSelectedItemId(R.id.action_help);

        if (getIntent().getExtras()!= null){
            switch (getIntent().getExtras().getString("action")){
                case("3"):
                    FragmentTransaction ft;
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.help_container, new TrafficFineFragment());
                    ft.commit();
                    navigationView.setSelectedItemId(R.id.action_traffic_fines);

            }

        }else{

            FragmentTransaction ft;
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.help_container, new HelpFragment());
            ft.commit();
        }



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction ft;
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        switch (item.getItemId()) {
            case R.id.action_accidents:
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.help_container, new AccidentsFragment());
                ft.commit();
                break;

            case R.id.action_traffic_fines:
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.help_container, new TrafficFineFragment());
                ft.commit();
                break;

            case R.id.action_help:
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.help_container, new HelpFragment());
                ft.commit();
                break;
        }
        return true;
    }


}
