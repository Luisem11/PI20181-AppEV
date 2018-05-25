package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.MainActivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.MainActivity.Help.AccidentsFragment;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.MainActivity.Help.HelpFragment;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.MainActivity.Help.TrafficFineFragment;

import me.toptas.fancyshowcase.FancyShowCaseQueue;
import me.toptas.fancyshowcase.FancyShowCaseView;
import me.toptas.fancyshowcase.FocusShape;
import me.toptas.fancyshowcase.OnCompleteListener;
import me.toptas.fancyshowcase.OnViewInflateListener;

public class HelpActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FancyShowCaseView mFancyView;
    private FancyShowCaseQueue mQueue, mQueue2, mQueue3;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarpreferences);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        navigationView =  findViewById(R.id.bottom_navigation);
        navigationView.setItemIconTintList(null);
        navigationView.setOnNavigationItemSelectedListener(this);
        navigationView.setSelectedItemId(R.id.action_help);

        if (getIntent().getExtras()!= null){

                if (getIntent().getExtras().getBoolean("SC")){
                    Showcase();
                }else{
                    switch (getIntent().getExtras().getString("action")){
                        case("3"):
                            FragmentTransaction ft;
                            ft = getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.help_container, new TrafficFineFragment());
                            ft.commit();
                            navigationView.setSelectedItemId(R.id.action_traffic_fines);

                    }
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

    private void Showcase() {

        mQueue = new FancyShowCaseQueue();
        mQueue2 = new FancyShowCaseQueue();
        mQueue3 = new FancyShowCaseQueue();


        mFancyView = new FancyShowCaseView.Builder(this)
                .focusOn(findViewById(R.id.bottom_navigation))
                .delay(1000)
                .focusShape(FocusShape.ROUNDED_RECTANGLE)
                .roundRectRadius(90)
                .customView(R.layout.showcase_6, new OnViewInflateListener() {
                    @Override
                    public void onViewInflated(View view) {
                        view.findViewById(R.id.showcase_close)
                                .setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        mQueue.cancel(true);
                                    }
                                });

                    }
                })
                .closeOnTouch(true)
                .build();
        mQueue.add(mFancyView);

        mFancyView = new FancyShowCaseView.Builder(this)
                .customView(R.layout.showcase_7, new OnViewInflateListener() {
                    @Override
                    public void onViewInflated(View view) {
                        view.findViewById(R.id.showcase_close)
                                .setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        mQueue.cancel(true);
                                    }
                                });

                    }
                })
                .focusRectAtPosition(360, 320, 700, 400)
                .roundRectRadius(60)
                .closeOnTouch(true)
                .build();
        mQueue.add(mFancyView);


        mFancyView = new FancyShowCaseView.Builder(this)
                .delay(1000)
                .customView(R.layout.showcase_8, new OnViewInflateListener() {
                    @Override
                    public void onViewInflated(View view) {
                        view.findViewById(R.id.showcase_close)
                                .setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        mQueue2.cancel(true);
                                    }
                                });

                    }
                })
                .focusRectAtPosition(360, 350, 700, 450)
                .roundRectRadius(60)
                .closeOnTouch(true)
                .build();
        mQueue2.add(mFancyView);



        mFancyView = new FancyShowCaseView.Builder(this)
                .customView(R.layout.showcase_9, new OnViewInflateListener() {
                    @Override
                    public void onViewInflated(View view) {
                        view.findViewById(R.id.showcase_close)
                                .setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        mQueue3.cancel(true);
                                    }
                                });

                    }
                })
                .focusRectAtPosition(360, 870, 600, 200)
                .roundRectRadius(60)
                .closeOnTouch(true)
                .build();
        mQueue3.add(mFancyView);

        mQueue.show();
        mQueue.setCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete() {
                FragmentTransaction ft;
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.help_container, new AccidentsFragment());
                ft.commit();
                navigationView.setSelectedItemId(R.id.action_accidents);
                mQueue2.show();
            }
        });


        mQueue2.setCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete() {
                FragmentTransaction ft;
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.help_container, new TrafficFineFragment());
                ft.commit();
                navigationView.setSelectedItemId(R.id.action_traffic_fines);
                mQueue3.show();

            }
        });
        mQueue3.setCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete() {
                Intent intent = new Intent();
                intent.putExtra("SC_H", true);
                setResult(1002, intent);
                finish();
            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("N", navigationView.getSelectedItemId() );
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        FragmentTransaction ft;
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.help_container, new TrafficFineFragment());
        ft.commit();
        navigationView.setSelectedItemId(savedInstanceState.getInt("N"));

    }

}
