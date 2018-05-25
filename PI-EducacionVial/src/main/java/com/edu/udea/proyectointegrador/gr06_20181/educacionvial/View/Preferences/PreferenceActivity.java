package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.Preferences;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller.PreferenceAdapter;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.Preference;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.TipsReceiver;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.WeatherReciver;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PreferenceActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,  PreferenceAdapter.OnItemClickListener {

    private RecyclerView preference1List, preference2List;
    public CheckBox preferenceCheckBox;
    public Preference preference;
    PreferenceAdapter preferenceAdapter1, preferenceAdapter2;
    List<Preference> arrayOfPreferences, arrayOfPreferences2;
    SharedPreferences pref;
    SharedPreferences.Editor edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar_pref);


        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle(R.string.preference);


        preferenceAdapter1 = new PreferenceAdapter(null, this);
        preference1List = findViewById(R.id.list_preference);
        preference2List = findViewById(R.id.list_preference_2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        preference1List.setLayoutManager(linearLayoutManager);
        preference1List.setAdapter(preferenceAdapter1);
        preference1List.setHasFixedSize(true);


        preferenceAdapter2 = new PreferenceAdapter(null, this);
        linearLayoutManager = new LinearLayoutManager(this);
        preference2List.setLayoutManager(linearLayoutManager);
        preference2List.setAdapter(preferenceAdapter2);
        preference2List.setHasFixedSize(true);

        loadPreference();

    }


    private void loadPreference() {

        arrayOfPreferences = new ArrayList<>();

        arrayOfPreferences.add(new Preference("Tips de Cultura Vial", "Frecuencia de notificaciones a la semana", "null", "null"));
        arrayOfPreferences.add(new Preference("Pico y Placa", "Configurar notificaciones de pico y placa", "null", "null"));
        arrayOfPreferences.add(new Preference("Clima", "Recibir notificaciones del clima todas la mañanas", "null", "check"));
        preferenceAdapter1.swapCursor(arrayOfPreferences);
        arrayOfPreferences2 = new ArrayList<>();
        arrayOfPreferences2.add(new Preference("Ubicación", "Cambiar la ubicación", "null", "null"));
        arrayOfPreferences2.add(new Preference("Modo Tutorial", "Activa el tutorial para la proxima inicies", "null", "check"));
        preferenceAdapter2.swapCursor(arrayOfPreferences2);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


    }

    private void showDialogFrecuency() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_tips_frecuency);
        ImageView close = dialog.findViewById(R.id.close_dialog_steps);
        Button save = dialog.findViewById(R.id.action_dialog_tips);
        final RadioGroup group = dialog.findViewById(R.id.group);

        View radioButton = group.getChildAt(pref.getInt("frecuency", 0));
        group.check(radioButton.getId());

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View radioButton = group.findViewById(group.getCheckedRadioButtonId());
                edit.putInt("frecuency", group.indexOfChild(radioButton));
                edit.commit();
                dialog.dismiss();
                notificateTips(view.getContext(), group.indexOfChild(radioButton));
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }

    private void openLocationDialog(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_location_preference);
        ImageView close = dialog.findViewById(R.id.close_dialog_steps);
        Button save = dialog.findViewById(R.id.save_location);
        final TextView title = dialog.findViewById(R.id.edit);
        final Spinner option = dialog.findViewById(R.id.spinner2);

        title.setText(pref.getString("location", "Medellín"));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.location_array, R.layout.item_spinner);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);   //Set a default layout for items
        option.setAdapter(adapter);

        option.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (option.getSelectedItemId() != 0) {
                    title.setText(option.getSelectedItem().toString());

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.putString("location", title.getText().toString());
                edit.commit();
                dialog.dismiss();
            }
        });


        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void openPYPDialog(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_p_y_p);
        ImageView close = dialog.findViewById(R.id.close_dialog);
        Button save = dialog.findViewById(R.id.action_dialog_pyp);
        final Switch monday = dialog.findViewById(R.id.switch_pyp_m);
        final Switch tuesday = dialog.findViewById(R.id.switch_pyp_t);
        final Switch wednesday = dialog.findViewById(R.id.switch_pyp_w);
        final Switch thursday = dialog.findViewById(R.id.switch_pyp_tu);
        final Switch friday = dialog.findViewById(R.id.switch_pyp_f);

        monday.setChecked(pref.getBoolean("monday", false));
        tuesday.setChecked(pref.getBoolean("tuesday", false));
        wednesday.setChecked(pref.getBoolean("wednesday", false));
        thursday.setChecked(pref.getBoolean("thursday", false));
        friday.setChecked(pref.getBoolean("friday", false));
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.putBoolean("monday",monday.isChecked());
                edit.putBoolean("tuesday",tuesday.isChecked());
                edit.putBoolean("wednesday",wednesday.isChecked());
                edit.putBoolean("thursday",thursday.isChecked());
                edit.putBoolean("friday",friday.isChecked());
                edit.commit();
                dialog.dismiss();

            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void notificateTips(Context context, int frecuency) {


        Intent intent = new Intent(context, TipsReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(),
                100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        switch (frecuency){
            case 0:
                alarmManager.cancel(pendingIntent);
                Toast.makeText(context, "Cancelado", Toast.LENGTH_SHORT).show();

                break;

            case 1:
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                calendar.set(Calendar.HOUR_OF_DAY, 12);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);

                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                        calendar.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY*10, pendingIntent);
                Toast.makeText(context, "Programado", Toast.LENGTH_SHORT).show();
                break;

            case 2:
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                calendar.set(Calendar.HOUR_OF_DAY, 12);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);

                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                        calendar.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY*8, pendingIntent);

                Toast.makeText(context, "Programado", Toast.LENGTH_SHORT).show();
                break;

            case 3:
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 5);
                calendar.set(Calendar.SECOND, 0);

                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                        calendar.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY*3, pendingIntent);

                Toast.makeText(context, "Programado", Toast.LENGTH_SHORT).show();
                break;

        }


    }

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

            Toast.makeText(c, "Programado recordatoria para las 6:00 AM", Toast.LENGTH_SHORT).show();
        } else {

            alarmManager.cancel(pendingIntent);

            Toast.makeText(c, "Cancelado", Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public void onClick(PreferenceAdapter.PreferenceViewHolder holder, int id, View view, Preference prefer) {


        pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        edit = pref.edit();
        preferenceCheckBox = (CheckBox) view.findViewById(R.id.pref_checkbox);
        preference = prefer;

        preferenceCheckBox.setChecked(!preferenceCheckBox.isChecked());

        switch (preference.getTitle()) {
            case "Pico y Placa":
                openPYPDialog(view.getContext());
                edit.putBoolean("pyp", preferenceCheckBox.isChecked());
                edit.commit();
                break;

            case "Clima":
                notificateWeather(view.getContext(), preferenceCheckBox.isChecked());
                edit.putBoolean("weather", preferenceCheckBox.isChecked());
                edit.commit();
                break;

            case "Tips de Cultura Vial":
                showDialogFrecuency();
                edit.putBoolean("tips", !pref.getBoolean("tips", false));
                edit.commit();
                break;

            case "Ubicación":
                openLocationDialog(view.getContext());
                break;

            case "Modo Tutorial":
                edit.putBoolean("showcase", preferenceCheckBox.isChecked());
                edit.commit();
                break;
        }


    }

}

