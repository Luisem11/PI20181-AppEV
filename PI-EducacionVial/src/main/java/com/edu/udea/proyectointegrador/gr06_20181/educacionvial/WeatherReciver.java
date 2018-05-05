package com.edu.udea.proyectointegrador.gr06_20181.educacionvial;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller.WeatherRequest;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.MainActivity.RoadCultureActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by luisernesto on 20/04/18.
 */

public class WeatherReciver extends BroadcastReceiver {
    String[] result;
    Context c;

    @Override
    public void onReceive(Context context, Intent intent) {

        c = context;

        new HttpGetTask().execute();

    }


    @SuppressLint("StaticFieldLeak")
    private class HttpGetTask extends AsyncTask<Void, Void, String> {

        private String data;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(Void... params) {
            String result = null;
            WeatherRequest client = new WeatherRequest("" + 6.24, "-" + 75.57);
            data = client.httpGet();

            JSONObject responseObject;
            try {

                responseObject = new JSONObject(data);
                JSONObject jsonObject = (JSONObject) responseObject.getJSONArray("weather").get(0);
                result = "" + jsonObject.get("id");

            } catch (JSONException e) {

                e.printStackTrace();
            }


            return result;
        }

        @Override
        protected void onPostExecute(String dataList) {

            result = getMessage(dataList);
            presentHeadsUpNotification(Notification.VISIBILITY_PUBLIC, R.drawable.ic_notification_icon, result[0], result[1], result[2]);


        }

        private String[] getMessage(String dataList) {
            String[] result = new String[3];
            switch (dataList.charAt(0)) {
                case '2': //Tormenta
                    result[0] = "Cuidado con la LLuvia!";
                    result[1] = "Trata de buscar refugio";
                    result[2] = "Recuerda llevar el equipo adecuado y ser prudente en la via, así podemos evitar accidentes";
                    break;
                case '3': //llovizna
                    result[0] = "Hay Probabilidades de Lluvia";
                    result[1] = "Ten precaución!";
                    result[2] = "Ten precaución! Con el suelo mojado manejar se hace mas difícil";
                    break;
                case '5': //Lluvia
                    result[0] = "Cuidado con la LLuvia!";
                    result[1] = "Recuerda llevar el equipo adecuado";
                    result[2] = "Recuerda llevar el equipo adecuado y ser prudente en la via, así podemos evitar accidentes";
                    break;
                case '8': //Despejado
                    switch (dataList.charAt(2)){
                        case 0://Cielo despejado
                            result[0] = "Cielo despejado";
                            result[1] = "Tal vez hoy sea un buen día, pero es bueno estar preparado";
                            result[2] = "Tal vez hoy sea un buen día, pero es bueno estar preparado";
                            return result;
                        case 1:
                            result[0] = "Hay pocas nubes!";
                            break;
                        case 2:
                            result[0] = "Está un poco nubado!";
                            break;
                        default:
                            result[0] = "Esta bastante nubado!";
                            break;

                    }
                    result[1] = "Recuerda estar preparado";
                    result[2] = "Recuerda estar preparado, no vaya a ser que comience a llover y no tengas el equipo adecuado";
                    break;
                    default:
                        result[0] = "El Clima esta loco!";
                        result[1] = "Recuerda estar preparado";
                        result[2] = "Recuerda estar preparado, no vaya a ser que comience a llover y no tengas el equipo adecuado";
                        break;

            }
            return result;
        }


    }

    private void presentHeadsUpNotification(int visibility, int icon, String title, String text, String bigtext) {
        Intent notificationIntent = new Intent(c, RoadCultureActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(c, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(c)
                .setCategory(Notification.CATEGORY_PROMO)
                .setContentTitle(title)
                .setSmallIcon(icon)
                .setContentText(text)
                .setAutoCancel(true)
                .setVisibility(visibility)
                .addAction(android.R.drawable.ic_menu_view, c.getString(R.string.road_education), contentIntent)
                .setContentIntent(contentIntent)
                .setStyle(new Notification.BigTextStyle()
                        .bigText(bigtext))
                .setPriority(Notification.PRIORITY_HIGH)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000}).build();
        NotificationManager notificationManager =
                (NotificationManager) c.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(100, notification);
    }

}
