package com.edu.udea.proyectointegrador.gr06_20181.educacionvial;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class IntroActivity extends AppCompatActivity  {
    final long Delay = 3000;
    boolean motoisChecked;
    boolean bikeisChecked;
    boolean carisChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarpreferences);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setIcon(R.drawable.ic_launcherico);
        getSupportActionBar().hide();


        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, new SplashScreenFragment());
        ft.commit();

        // Create a Timer
        Timer RunSplash = new Timer();

        // Task to do when the timer ends
        final TimerTask ShowSplash = new TimerTask() {
            @Override
            public void run() {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, new InitialPreferencesFragment());
                ft.commit();
            }
        };

        // Start the timer
        RunSplash.schedule(ShowSplash, Delay);


        motoisChecked = false;
        bikeisChecked = false;
        carisChecked = false;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_preferences, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        ImageView imageView;
        switch(view.getId()){
            case R.id.nextButton:
                if(!carisChecked&&!bikeisChecked&&!motoisChecked){
                    Toast.makeText(this,"Debe seleccionar al menos una opcion",Toast.LENGTH_SHORT).show();
                    break;
                }
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.bike:
                if(bikeisChecked){
                    imageView = view.findViewById(R.id.bike);
                    imageView.setImageResource(R.drawable.ic_bicycle);
                    bikeisChecked  = false;
                }else{
                    imageView = view.findViewById(R.id.bike);
                    imageView.setImageResource(R.drawable.ic_bicycle_);
                    bikeisChecked = true;
                }
                break;
            case R.id.moto:
                if(motoisChecked){
                    imageView = view.findViewById(R.id.moto);
                    imageView.setImageResource(R.drawable.ic_motocycle);
                    motoisChecked = false;
                }else{
                    imageView = view.findViewById(R.id.moto);
                    imageView.setImageResource(R.drawable.ic_motocycle_);
                    motoisChecked = true;
                }

                break;
            case R.id.car:
                if(carisChecked){
                    imageView = view.findViewById(R.id.car);
                    imageView.setImageResource(R.drawable.ic_car);
                    carisChecked = false;
                }else{
                    imageView = view.findViewById(R.id.car);
                    imageView.setImageResource(R.drawable.ic_car_);
                    carisChecked = true;
                }
                break;
        }
    }

}
