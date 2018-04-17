package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public class LocationRequest {

    private  LocationManager locationManager;
    private double longitudeGPS, latitudeGPS;
    private double longitudeNetwork, latitudeNetwork;
    Context context;

    public LocationRequest(LocationManager locationManager, Context c) {
        this.locationManager = locationManager;
        context = c.getApplicationContext();
    }

    private boolean checkLocation() {
        if (!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("Enable Location")
                .setMessage("Su ubicación esta desactivada.\npor favor active su ubicación " +
                        "usa esta app")
                .setPositiveButton("Configuración de ubicación", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        context.startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    }
                });
        dialog.show();
    }

    private boolean isLocationEnabled() {
        return
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public void toggleGPSUpdates() {
        if (!checkLocation())
            return;

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        //locationManager.requestLocationUpdates(
              //  LocationManager.GPS_PROVIDER, 2 * 20 * 1000, 10, locationListenerGPS);

    }

    public void toggleNetworkUpdates() {
        if (!checkLocation())
            return;
//        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//            return;
//        }
        //locationManager.requestLocationUpdates(
          //      LocationManager.NETWORK_PROVIDER, 20 * 1000, 10, locationListenerNetwork);
       // Toast.makeText(context, "Network provider started running", Toast.LENGTH_LONG).show();

    }


    public double getLongitudeGPS() {
        return longitudeGPS;
    }

    public void setLongitudeGPS(double longitudeGPS) {
        this.longitudeGPS = longitudeGPS;
    }

    public double getLatitudeGPS() {
        return latitudeGPS;
    }

    public void setLatitudeGPS(double latitudeGPS) {
        this.latitudeGPS = latitudeGPS;
    }

    public double getLongitudeNetwork() {
        return longitudeNetwork;
    }

    public void setLongitudeNetwork(double longitudeNetwork) {
        this.longitudeNetwork = longitudeNetwork;
    }

    public double getLatitudeNetwork() {
        return latitudeNetwork;
    }

    public void setLatitudeNetwork(double latitudeNetwork) {
        this.latitudeNetwork = latitudeNetwork;
    }

}
