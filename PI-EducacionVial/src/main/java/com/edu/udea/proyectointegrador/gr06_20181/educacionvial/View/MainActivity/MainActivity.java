package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.MainActivity;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller.ImageAdapter;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller.WeatherRequest;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.Preferences.PreferenceFragmentCustom;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    LocationManager locationManager;
    double longitudeGPS, latitudeGPS;
    double longitudeNetwork, latitudeNetwork;
    String[] result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        locationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);


    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (item.getItemId()){
            case R.id.weather:

                new HttpGetTask().execute();
                break;
            case R.id.settings:

                break;

        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void onClick(View view) {

        Intent intent = null;
        switch (view.getId()){

            case R.id.roadcardId:
                intent = new Intent(MainActivity.this, RoadCultureActivity.class);
                break;
            case R.id.rulescardId:
                //intent = new Intent(MainActivity.this, RulesActivity.class);
                break;
            case R.id.helpcardId:
                intent = new Intent(MainActivity.this, HelpActivity.class);
                break;
            case R.id.gamescardId:
                //intent = new Intent(MainActivity.this, GamesActivity.class);
                break;

        }
        if (intent != null) {
            startActivity(intent);
        }
        else{
            Snackbar.make(view,"En proceso de desarrollo...",Snackbar.LENGTH_SHORT).show();
        }

    }

    @SuppressLint("StaticFieldLeak")
    private class HttpGetTask extends AsyncTask<Void, Void, String> {

        private ProgressDialog Dialog = new ProgressDialog(MainActivity.this);
        private String data;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //Start Progress Dialog (Message)
            Dialog.setMessage("Please wait..");
            Dialog.show();
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
            // Close progress dialog
            Dialog.dismiss();
            presentHeadsUpNotification(Notification.VISIBILITY_PUBLIC, R.drawable.ic_launcherico,result[0],result[1],result[2]);



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
                    result[0] = "Hay probabilidades de lluvia";
                    result[1] = "Recuerda llevar el equipo adecuado y ser prudente en la via, así podemos evitar accidentes";
                    result[2] = "Recuerda llevar el equipo adecuado y ser prudente en la via, así podemos evitar accidentes";
                    break;
                case '5': //Lluvia
                    result[0] = "Cuidado con la LLuvia!";
                    result[1] = "Recuerda llevar el equipo adecuado y ser prudente en la via, así podemos evitar accidentes";
                    result[2] = "Recuerda llevar el equipo adecuado y ser prudente en la via, así podemos evitar accidentes";
                    break;
                case '8': //Despejado
                    result[0] = "Cielo despejado";
                    result[1] = "Recuerda";
                    result[2] = "Recuerda";
                    break;
            }
            return result;
        }


    }

    private void presentHeadsUpNotification(int visibility, int icon, String title, String text, String bigtext) {
        Intent notificationIntent = new Intent(this, RoadCultureActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Notification notification = new Notification.Builder(this)
                .setCategory(Notification.CATEGORY_PROMO)
                .setContentTitle(title)
                .setSmallIcon(icon)
                .setContentText(text)
                .setAutoCancel(true)
                .setVisibility(visibility)
                .addAction(android.R.drawable.ic_menu_view, getString(R.string.road_education), contentIntent)
                .setContentIntent(contentIntent)
                .setStyle(new Notification.BigTextStyle()
                        .bigText(bigtext))
                .setPriority(Notification.PRIORITY_HIGH)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000}).build();
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(2, notification);
    }

    private final LocationListener locationListenerGPS = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitudeGPS = location.getLongitude();
            latitudeGPS = location.getLatitude();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "GPS Provider update", Toast.LENGTH_SHORT).show();
                    locationManager.removeUpdates(locationListenerGPS);
                    presentHeadsUpNotification(Notification.VISIBILITY_PUBLIC, R.drawable.ic_launcherico,result[0],result[1],result[2]);

                }
            });
        }
        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
        }

        @Override
        public void onProviderEnabled(String s) {
        }
        @Override
        public void onProviderDisabled(String s) {
        }
    };

    private final LocationListener locationListenerNetwork = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitudeNetwork = location.getLongitude();
            latitudeNetwork = location.getLatitude();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "Network Provider update", Toast.LENGTH_SHORT).show();
                    locationManager.removeUpdates(locationListenerNetwork);
                }
            });
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
        }

        @Override
        public void onProviderEnabled(String s) {

        }
        @Override
        public void onProviderDisabled(String s) {

        }
    };

}
