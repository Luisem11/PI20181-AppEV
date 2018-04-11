package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.MainActivity;

import android.database.Cursor;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller.TipsAdapter;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB.DbHelper;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;

public class RoadCultureActivity extends AppCompatActivity implements View.OnClickListener {

    public TipsAdapter tipsAdapter;
    private DbHelper tipsDbHelper;
    private RecyclerView tipsList;
    private FloatingActionButton mAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_culture);



        tipsList = (RecyclerView) findViewById(R.id.rv_content);
        mAddButton = (FloatingActionButton) findViewById(R.id.fab);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        mAddButton.setOnClickListener(this);
        tipsAdapter = new TipsAdapter(null);
        tipsDbHelper = new DbHelper(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        tipsList.setLayoutManager(linearLayoutManager);

        tipsList.setAdapter(tipsAdapter);
        tipsList.setHasFixedSize(true);
        loadTips();



    }

    private void loadTips() {
        new TipsLoadTask().execute();
    }

    @Override
    public void onClick(View view) {

    }

    private class TipsLoadTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return tipsDbHelper.getAllTips();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.getCount() > 0) {
                tipsAdapter.swapCursor(cursor);
            } else {
                // Mostrar empty state
            }
        }
    }

}
