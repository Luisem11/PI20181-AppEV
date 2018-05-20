package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.MainActivity;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller.WeatherRequest;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.Preferences.PreferenceActivity;

import org.json.JSONException;
import org.json.JSONObject;

import me.toptas.fancyshowcase.FancyShowCaseQueue;
import me.toptas.fancyshowcase.FancyShowCaseView;
import me.toptas.fancyshowcase.FocusShape;
import me.toptas.fancyshowcase.OnCompleteListener;
import me.toptas.fancyshowcase.OnViewInflateListener;

public class MainActivity extends AppCompatActivity {


    LocationManager locationManager;
    double longitudeGPS, latitudeGPS;
    double longitudeNetwork, latitudeNetwork;
    String[] result;
    private FancyShowCaseView mFancyView, mFancyView2;
    private FancyShowCaseQueue mQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();

        if(!pref.contains("showcase")||pref.getBoolean("showcase",false)){
            edit.putBoolean("showcase", false);
            edit.commit();
            Showcase();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
//            case R.id.weather:
//
//                new HttpGetTask().execute();
//                break;
            case R.id.settings:
                Intent intent = new Intent(MainActivity.this, PreferenceActivity.class);
                startActivity(intent);
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
            presentHeadsUpNotification(Notification.VISIBILITY_PUBLIC, R.drawable.ic_notification_icon,result[0],result[1],result[2]);



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
        Intent notificationIntent = new Intent(this, RoadCultureActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Notification notification = new Notification.Builder(this)
                .setCategory(Notification.CATEGORY_PROMO)
                .setContentTitle(title)
                .setSmallIcon(icon)
                .setContentText(text)
                .setColor(ContextCompat.getColor(this, R.color.colorPrimary))
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 100){
            if(resultCode == 1001 && data.getBooleanExtra("SC_RCA",false)){
                Intent intent = new Intent(MainActivity.this, HelpActivity.class);
                intent.putExtra("SC", true);
                startActivityForResult(intent, 100);
            }
            if(resultCode == 1002 && data.getBooleanExtra("SC_H",false)){
                ShowcaseEnd();

            }

        }
    }

    private void Showcase() {
        mFancyView = new FancyShowCaseView.Builder(this)
                .customView(R.layout.showcase_1, new OnViewInflateListener() {
                    @Override
                    public void onViewInflated(View view) {
                        view.findViewById(R.id.showcase_close)
                                .setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        mQueue.cancel(true);
                                    }
                                });
                        view.findViewById(R.id.showcase_next)
                                .setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        mFancyView.hide();
                                    }
                                });

                    }
                })
                .closeOnTouch(false)
                .build();

        mFancyView2 = new FancyShowCaseView.Builder(this)
                .focusOn(findViewById(R.id.linear_layout_main))
                .focusShape(FocusShape.ROUNDED_RECTANGLE)
                .roundRectRadius(90)
                .disableFocusAnimation()
                .customView(R.layout.showcase_2, new OnViewInflateListener() {
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


        mQueue = new FancyShowCaseQueue();
        mQueue.add(mFancyView);
        mQueue.add(mFancyView2);
        mQueue.setCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete() {
                Intent intent = new Intent(MainActivity.this, RoadCultureActivity.class);
                intent.putExtra("SC", true);
                startActivityForResult(intent, 100);
            }
        });
        mQueue.show();
    }


    private void ShowcaseEnd() {
        mFancyView = new FancyShowCaseView.Builder(this)
                .customView(R.layout.showcase_10, new OnViewInflateListener() {
                    @Override
                    public void onViewInflated(View view) {
                    }
                })
                .focusOn(findViewById(R.id.settings))
                .closeOnTouch(true)
                .focusRectAtPosition(675, 105, 80, 80)
                .roundRectRadius(60)
                .build();


        mQueue = new FancyShowCaseQueue();
        mQueue.add(mFancyView);
        mQueue.setCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete() {
            }
        });
        mQueue.show();
    }


}
