package com.edu.udea.proyectointegrador.gr06_20181.educacionvial;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.NotificationCompat;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB.DbHelper;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB.Tip;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.MainActivity.RCDetailsActivity;

public class TipsReceiver extends BroadcastReceiver {

    Context c;

    private DbHelper tipsDbHelper;

    @Override
    public void onReceive(Context context, Intent intent) {

        c = context;
        tipsDbHelper = new DbHelper(context);
        new NotificationLoadTask().execute();


    }

    private class NotificationLoadTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return tipsDbHelper.getAllTips();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.getCount() > 0) {
                int id = (int) (Math.random() * cursor.getCount());
                cursor.move(id);
                Tip tip = new Tip(cursor);
                presentHeadsUpNotification(Notification.VISIBILITY_PUBLIC, R.drawable.ic_notification_icon, tip.getTitle(), tip.getSubtitle(), tip.getSubtitle(), tip.getId());

            } else {
                // Mostrar empty state
            }
        }
    }

    private void presentHeadsUpNotification(int visibility, int icon, String title, String text, String bigtext, int id) {
        Intent notificationIntent = new Intent(c, RCDetailsActivity.class);
        notificationIntent.putExtra("ID", id);
        PendingIntent contentIntent = PendingIntent.getActivity(c, 0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(c)
                .setCategory(Notification.CATEGORY_PROMO)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(icon)
                .setColor(Color.BLUE)
                .setAutoCancel(true)
                .setVisibility(visibility)
                .addAction(android.R.drawable.ic_menu_view,c.getString(R.string.road_education), contentIntent)
                .setContentIntent(contentIntent)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(bigtext))
                .setPriority(Notification.PRIORITY_HIGH)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000}).build();
        NotificationManager notificationManager =
                (NotificationManager) c.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(2, notification);
    }


}
