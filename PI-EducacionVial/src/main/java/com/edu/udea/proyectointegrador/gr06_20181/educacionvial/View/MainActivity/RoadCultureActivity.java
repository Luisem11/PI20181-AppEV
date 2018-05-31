package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.MainActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller.AnimationU;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller.TipsAdapter;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB.DbHelper;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB.StatusContract;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;

import java.util.ArrayList;
import java.util.List;

public class RoadCultureActivity extends AppCompatActivity implements View.OnClickListener, TipsAdapter.OnItemClickListener {

    public TipsAdapter tipsAdapter;
    private DbHelper tipsDbHelper;
    private RecyclerView tipsList;
    private FloatingActionButton mAddButton;
    private boolean typeOn;
    private Spinner spinner;
    private LinearLayout filterRelativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_culture);


        tipsList = (RecyclerView) findViewById(R.id.rv_content);
        mAddButton = (FloatingActionButton) findViewById(R.id.fab);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        filterRelativeLayout = (LinearLayout) findViewById(R.id.filter);
        spinner = (Spinner) findViewById(R.id.type_spinner);
        typeOn = false;


        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().getBoolean("SC")) {
                tipsAdapter = new TipsAdapter(null, this, this, true, mAddButton);
            } else {

                tipsAdapter = new TipsAdapter(null, this, this);
            }

        } else {
            tipsAdapter = new TipsAdapter(null, this, this);
        }

        mAddButton.setOnClickListener(this);
        tipsDbHelper = new DbHelper(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        tipsList.setLayoutManager(linearLayoutManager);
        tipsList.setAdapter(tipsAdapter);
        LayoutAnimationController controller = android.view.animation.AnimationUtils.loadLayoutAnimation(this, R.anim.layout_fall_down);
        tipsList.setLayoutAnimation(controller);
        tipsList.setHasFixedSize(true);
        tipsList.scheduleLayoutAnimation();
        loadTips();
        SQLiteDatabase db = new DbHelper(this).getReadableDatabase();

        Cursor cursor = db.query(StatusContract.TABLE_TYPE, null, null, null, null, null, null);

        List<String> subjects = new ArrayList<>();
        subjects.add("");
        for (boolean b = cursor.moveToFirst(); b; b = cursor.moveToNext()) {

            subjects.add(cursor.getString(cursor.getColumnIndex(StatusContract.Column_Type.NAME)));

        }
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.spinner_text, subjects);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);   //Set a default layout for items
        spinner.setAdapter(adapter);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                switch ((int) spinner.getSelectedItemId()) {
                    case 0:
                        loadTips();
                        break;
                    case 1:
                        new TipsFindTask().execute(1);
                        break;
                    case 2:
                        new TipsFindTask().execute(2);
                        break;
                    case 3:
                        new TipsFindTask().execute(3);
                        break;
                    case 4:
                        new TipsFindTask().execute(4);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_road_c, menu);

        final MenuItem item = menu.findItem(R.id.random);

        item.setActionView(R.layout.item_random);
        final ImageView refresh = (ImageView) item.getActionView().findViewById(R.id.refreshButton);

        refresh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Animation rotation = AnimationUtils.loadAnimation(v.getContext(), R.anim.rotation);
                refresh.startAnimation(rotation);

                onOptionsItemSelected(item);
            }
        });
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {

            case R.id.random:
                LayoutAnimationController controller = android.view.animation.AnimationUtils.loadLayoutAnimation(this, R.anim.layout_fall_down);
                tipsList.setLayoutAnimation(controller);
                tipsList.setHasFixedSize(true);
                tipsList.scheduleLayoutAnimation();
                loadTips();
                break;


        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.type:
                CardView cardView1 = (CardView) view.findViewById(R.id.card1);
                CardView cardView2 = (CardView) view.findViewById(R.id.card2);
                CardView cardView3 = (CardView) view.findViewById(R.id.card3);
                CardView cardView4 = (CardView) view.findViewById(R.id.card4);
                TextView textView1 = (TextView) view.findViewById(R.id.textv1);
                TextView textView2 = (TextView) view.findViewById(R.id.textv2);
                TextView textView3 = (TextView) view.findViewById(R.id.textv3);
                TextView textView4 = (TextView) view.findViewById(R.id.textv4);
                if (!typeOn) {
                    final float scale = getResources().getDisplayMetrics().density;
                    LinearLayout.LayoutParams cardLayoutParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    cardLayoutParams.setMargins((int) (4 * scale + 0.5f), 0, 0, 0);
                    cardView1.setLayoutParams(cardLayoutParams);
                    textView1.setVisibility(View.VISIBLE);
                    cardView2.setLayoutParams(cardLayoutParams);
                    textView2.setVisibility(View.VISIBLE);
                    cardView3.setLayoutParams(cardLayoutParams);
                    textView3.setVisibility(View.VISIBLE);
                    cardView4.setLayoutParams(cardLayoutParams);
                    textView4.setVisibility(View.VISIBLE);
                    typeOn = true;
                } else {
                    final float scale = getResources().getDisplayMetrics().density;
                    LinearLayout.LayoutParams cardLayoutParams = new LinearLayout.LayoutParams((int) (30 * scale + 0.5f), (int) (10 * scale + 0.5f));
                    cardLayoutParams.setMargins((int) (4 * scale + 0.5f), 0, 0, 0);
                    textView1.setVisibility(View.GONE);
                    cardView1.setLayoutParams(cardLayoutParams);
                    textView2.setVisibility(View.GONE);
                    cardView2.setLayoutParams(cardLayoutParams);
                    textView3.setVisibility(View.GONE);
                    cardView3.setLayoutParams(cardLayoutParams);
                    textView4.setVisibility(View.GONE);
                    cardView4.setLayoutParams(cardLayoutParams);
                    typeOn = false;
                }
                break;

            case R.id.fab:
                //new NotificationLoadTask().execute();
                AnimationU.slideLeftOpen(filterRelativeLayout);
                mAddButton.hide();
                spinner.setSelection(0);


                break;

            case R.id.back:

                AnimationU.slideLeftClose(filterRelativeLayout);
                loadTips();
                mAddButton.show();
                break;

            case R.id.type_image:
                spinner.performClick();
                break;
        }

    }

    private void loadTips() {
        new TipsLoadTask().execute();
    }

    @Override
    public void onClick(TipsAdapter.TipsViewHolder holder, int idTip) {

        Intent intent = new Intent(this, RCDetailsActivity.class);
        intent.putExtra("ID", idTip);
        startActivity(intent);

    }

    private class TipsLoadTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return tipsDbHelper.getAllTips();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.getCount() > 0) {
                tipsAdapter.swapCursor(cursor, tipsDbHelper);
            } else {
                // Mostrar empty state
            }
        }
    }

    private class TipsFindTask extends AsyncTask<Integer, Void, Cursor> {


        @Override
        protected Cursor doInBackground(Integer... integers) {
            return tipsDbHelper.getTipByType(integers[0]);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.getCount() > 0) {
                tipsAdapter.swapCursor(cursor, tipsDbHelper);
            } else {
                // Mostrar empty state
            }
        }
    }


}
