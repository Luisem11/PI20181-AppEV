package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.MainActivity;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB.DbHelper;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB.Tip;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;

public class RCDetailsActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    private int TipId;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private DbHelper mLawyersDbHelper;
    private AppBarLayout appBarLayout;
    private TextView titleTextView;

    private String title;
    boolean isShow = false;
    int scrollRange = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rcdetails);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        titleTextView = (TextView) findViewById(R.id.title1);
        mLawyersDbHelper = new DbHelper(this);
        title = "";



        TipId = -1;
        if (getIntent().getExtras() != null) {
            TipId = getIntent().getExtras().getInt("ID");
        }
        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(this);

        loadTip();
    }

    private void loadTip() {
        new GetLawyerByIdTask().execute();
    }

    private void showTip(Tip tip) {
        title = tip.getTitle();
        titleTextView.setText(tip.getTitle());

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.lawyer_detail_container, new Details1Fragment(tip));
        ft.commit();

    }

    private void showLoadError() {
        Toast.makeText(this,
                R.string.error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

        if (scrollRange == -1) {
            scrollRange = appBarLayout.getTotalScrollRange();
        }
        if (scrollRange + verticalOffset == 0) {
            //when collapsingToolbar at that time display actionbar title
            collapsingToolbarLayout.setTitle(title);
            isShow = true;
        } else if (isShow) {
            //carefull there must a space between double quote otherwise it dose't work
            collapsingToolbarLayout.setTitle(" ");
            isShow = false;
        }


    }

    private class GetLawyerByIdTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mLawyersDbHelper.getTipById(TipId);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.moveToLast()) {
                showTip(new Tip(cursor));
            } else {
                showLoadError();
            }
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
