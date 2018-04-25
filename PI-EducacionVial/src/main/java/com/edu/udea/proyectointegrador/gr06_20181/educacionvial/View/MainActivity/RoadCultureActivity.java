package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.MainActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller.AnimationUtils;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller.TipsAdapter;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB.DbHelper;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB.Tip;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;

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


        mAddButton.setOnClickListener(this);
        tipsAdapter = new TipsAdapter(null, this);
        tipsDbHelper = new DbHelper(this);
        LayoutAnimationController controller = android.view.animation.AnimationUtils.loadLayoutAnimation(this, R.anim.layout_fall_down);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        tipsList.setLayoutManager(linearLayoutManager);
        tipsList.setAdapter(tipsAdapter);
        tipsList.setLayoutAnimation(controller);
        tipsList.setHasFixedSize(true);
        loadTips();
        tipsList.scheduleLayoutAnimation();

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.types_array, R.layout.spinner_text);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
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
                    cardView1.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    textView1.setVisibility(View.VISIBLE);
                    cardView2.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    textView2.setVisibility(View.VISIBLE);
                    cardView3.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    textView3.setVisibility(View.VISIBLE);
                    cardView4.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    textView4.setVisibility(View.VISIBLE);
                    typeOn = true;
                } else {
                    final float scale = getResources().getDisplayMetrics().density;
                    LinearLayout.LayoutParams cardLayoutParams = new LinearLayout.LayoutParams((int) (30 * scale + 0.5f), (int) (10 * scale + 0.5f));
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
                AnimationUtils.slideLeftOpen(filterRelativeLayout);
                mAddButton.hide();
                spinner.setSelection(0);


                break;

            case R.id.back:

                AnimationUtils.slideLeftClose(filterRelativeLayout);
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

    private class NotificationLoadTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return tipsDbHelper.getAllTips();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                Tip tip = new Tip(cursor);
                presentHeadsUpNotification(Notification.VISIBILITY_PUBLIC, R.drawable.ic_not, tip.getTitle(), tip.getSubtitle(), tip.getSubtitle(), tip.getId());

            } else {
                // Mostrar empty state
            }
        }
    }

    private void presentHeadsUpNotification(int visibility, int icon, String title, String text, String bigtext, int id) {
        Intent notificationIntent = new Intent(this, RCDetailsActivity.class);
        notificationIntent.putExtra("ID", id);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this)
                .setCategory(Notification.CATEGORY_PROMO)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(icon)
                .setAutoCancel(true)
                .setVisibility(visibility)
                .addAction(android.R.drawable.ic_menu_view, getString(R.string.road_education), contentIntent)
                .setContentIntent(contentIntent)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(bigtext))
                .setPriority(Notification.PRIORITY_HIGH)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000}).build();
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(2, notification);
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

}
