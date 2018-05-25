package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.MainActivity;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB.DbHelper;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB.Tip;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;

public class RCDetailsActivity extends AppCompatActivity {

    private int TipId;
    private DbHelper mTipsDbHelper;
    private TextView titleTextView, body1TextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rcdetails);

       titleTextView = (TextView) findViewById(R.id.title1);
        body1TextView = (TextView) findViewById(R.id.body1);
        mTipsDbHelper = new DbHelper(this);



        TipId = -1;
        if (getIntent().getExtras() != null) {
            TipId = getIntent().getExtras().getInt("ID");
            loadTip();
        }

    }

    private void loadTip() {
        new GetTipByIdTask().execute();
    }

    private void showTip(Tip tip) {
        titleTextView.setText(tip.getTitle());
        body1TextView.setText(tip.getBody());

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.detail_container, new DetailsFragment(tip));
        ft.commit();

    }

    private void showLoadError() {
        Toast.makeText(this,
                R.string.error, Toast.LENGTH_SHORT).show();
    }

    private class GetTipByIdTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            if(TipId==-1){
                finish();
            }
            return mTipsDbHelper.getTipById(TipId);
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

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("N", TipId );
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TipId = savedInstanceState.getInt("N");
        if (TipId!=(-1)) {
            loadTip();
        }


    }

}
