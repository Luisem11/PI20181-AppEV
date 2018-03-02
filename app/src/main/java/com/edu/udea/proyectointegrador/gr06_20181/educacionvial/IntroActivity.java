package com.edu.udea.proyectointegrador.gr06_20181.educacionvial;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import static com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R.drawable.ic_bicycle_;

public class IntroActivity extends AppCompatActivity {
    final long Delay = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarPreferences);
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

    public void change(View view) {
        ImageView imageView = view.findViewById(R.id.bike);
        imageView.setImageResource(R.drawable.ic_bicycle_);
    }

    public void next(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
