package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.Preferences;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.widget.Toast;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.TipsReceiver;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.WeatherReciver;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;


public class PreferenceFragmentCustom extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {


    SharedPreferences.OnSharedPreferenceChangeListener listener;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        // Load the Preferences from the XML file
        addPreferencesFromResource(R.xml.preferences);
        Preference listPreference = findPreference("list_preference");
        listPreference.setOnPreferenceChangeListener(this);

    }


    @Override
    public boolean onPreferenceTreeClick(Preference preference) {


        switch (preference.getKey()) {
            case "session":

                Toast.makeText(preference.getContext(), "Pico y Placa", Toast.LENGTH_SHORT).show();
                break;
            case "weather":

                boolean b = preference.getSharedPreferences().getBoolean("weather", false);
                notificateWeather(preference.getContext(), b);
                break;

            case "list_preference":
                break;

        }
        return super.onPreferenceTreeClick(preference);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDisplayPreferenceDialog(Preference preference) {

        // Try if the preference is one of our custom Preferences
        DialogFragment dialogFragment = null;
        if (preference instanceof LocationPreference) {
            // Create a new instance of TimePreferenceDialogFragment with the key of the related
            // Preference
            dialogFragment = LocationPreferenceDialogFragmentCompat.newInstance(preference.getKey());
        }


        if (dialogFragment != null) {
            // The dialog was created (it was one of our custom Preferences), show the dialog for it
            dialogFragment.setTargetFragment(this, 0);
            dialogFragment.show(this.getFragmentManager(), "android.support.v7.preference" +
                    ".PreferenceFragment.DIALOG");
        } else {
            // Dialog creation could not be handled here. Try with the super method.
            super.onDisplayPreferenceDialog(preference);
        }

    }

    @SuppressLint("ServiceCast")
    public void notificateWeather(Context c, Boolean active) {

        Intent intent = new Intent(c, WeatherReciver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(c.getApplicationContext(),
                100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) c.getSystemService(ALARM_SERVICE);


        if (active) {
            Calendar calendar = Calendar.getInstance();

            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, 6);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

            Toast.makeText(c, "Programado", Toast.LENGTH_SHORT).show();
        } else {

            alarmManager.cancel(pendingIntent);

            Toast.makeText(c, "Cancelado", Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {


        Context c = preference.getContext();
        Intent intent = new Intent(c, TipsReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(c.getApplicationContext(),
                100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) c.getSystemService(ALARM_SERVICE);

        int i = Integer.parseInt((String) newValue);
        if (i == 1) {

            alarmManager.cancel(pendingIntent);
            Toast.makeText(c, "Cancelado", Toast.LENGTH_SHORT).show();

        } else {

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, 17);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(), AlarmManager.INTERVAL_HALF_HOUR, pendingIntent);

            Toast.makeText(c, "Programado", Toast.LENGTH_SHORT).show();
        }


        return false;
    }
}
