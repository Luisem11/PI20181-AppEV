package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.Preference;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.TipsReceiver;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.WeatherReciver;

import java.util.ArrayList;
import java.util.Calendar;

import static android.content.ContentValues.TAG;
import static android.content.Context.ALARM_SERVICE;

/**
 * Created by luisernesto on 3/05/18.
 */


public class PreferenceAdapter extends ArrayAdapter<Preference>  {
    public PreferenceAdapter(Context context, ArrayList<Preference> preferences) {
        super(context, 0, preferences);
    }

    public LinearLayout preferenceLinearLayout;
    public TextView preferenceTitle, preferenceDescription;
    public ImageButton preferenceAction;
    public CheckBox preferenceCheckBox;
    public Preference preference;
    public int frec;
    public String location;

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        // Get the data item for this position
        preference = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_preference
                    , parent, false);
        }
        SharedPreferences pref = itemView.getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Boolean weatherOn = pref.getBoolean("weather",true);
        Boolean pypOn = pref.getBoolean("pyp",false);
        String pypDays = pref.getString("days","null");
        Boolean tipsOn = pref.getBoolean("tips",false);
        int frecuency = pref.getInt("frecuency",1);
        String locationOn = pref.getString("location","Medellín");
        Boolean showcaseOn = pref.getBoolean("showcase",false);

        pref.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                Log.d("Preferences", "The key '" + key + "' is changed");
            }
        });



        // Lookup view for data population
        preferenceTitle = (TextView) itemView.findViewById(R.id.pref_title);
        preferenceDescription = (TextView) itemView.findViewById(R.id.pref_description);
        preferenceCheckBox = (CheckBox) itemView.findViewById(R.id.pref_checkbox);
        preferenceLinearLayout = itemView.findViewById(R.id.preference);


        preferenceTitle.setText(preference.getTitle());
        preferenceDescription.setText(preference.getDescription());
        if (preference.getType().equals("check")) {
            preferenceCheckBox.setVisibility(View.VISIBLE);
        }else {
            preferenceCheckBox.setVisibility(View.GONE);
        }

        switch (preference.getTitle()) {
            case "Pico y Placa":
                preferenceCheckBox.setChecked(pypOn);
                break;

            case "Clima":
                preferenceCheckBox.setChecked(weatherOn);
                break;

            case "Modo Tutorial":
                preferenceCheckBox.setChecked(showcaseOn);
                break;
        }

        return itemView;
    }

}
