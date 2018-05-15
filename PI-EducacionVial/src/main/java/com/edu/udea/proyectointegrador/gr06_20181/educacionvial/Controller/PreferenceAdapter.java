package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by luisernesto on 3/05/18.
 */


public class PreferenceAdapter extends ArrayAdapter<Preference> {
    public PreferenceAdapter(Context context, ArrayList<Preference> users) {
        super(context, 0, users);
    }

    public LinearLayout preferenceLinearLayout;
    public TextView preferenceTitle, preferenceDescription;
    public ImageButton preferenceAction;
    public CheckBox preferenceCheckBox;

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        // Get the data item for this position
        final Preference preference = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_preference
                    , parent, false);
        }
        // Lookup view for data population
        preferenceTitle = (TextView) itemView.findViewById(R.id.pref_title);
        preferenceAction = (ImageButton) itemView.findViewById(R.id.pref_icon);
        preferenceDescription = (TextView) itemView.findViewById(R.id.pref_description);
        preferenceCheckBox = (CheckBox) itemView.findViewById(R.id.pref_checkbox);
        preferenceLinearLayout = itemView.findViewById(R.id.preference);


        preferenceTitle.setText(preference.getTitle());
        preferenceDescription.setText(preference.getDescription());
        if(preference.getType().equals("check")){
            preferenceCheckBox.setVisibility(View.VISIBLE);
        }

        preferenceLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (preference.getTitle()){
                    case "Pico y Placa":


                        if(preferenceCheckBox.isChecked()){
                            preferenceCheckBox.setChecked(false);
                        }else {
                            preferenceCheckBox.setChecked(true);

                        }

                        openPYPDialog(view.getContext());
                        break;

                    case "Clima":

                        if(preferenceCheckBox.isChecked()){
                            preferenceCheckBox.setChecked(false);
                        }else {
                            preferenceCheckBox.setChecked(true);

                        }
                        notificateWeather(view.getContext(), preferenceCheckBox.isChecked());

                        break;

                    case "Tips de Cultura Vial":

                        notificateTips(view.getContext(), preferenceCheckBox.isChecked());

                        break;

                    case "Ubicación":

                        openLocationDialog(view.getContext());
                        break;
                }

            }
        });



        /// / Return the completed view to render on screen
        return itemView;
    }

    private void openLocationDialog(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_location_preference);
        ImageView close = dialog.findViewById(R.id.close_dialog_steps);
        final TextView title = dialog.findViewById(R.id.edit);
        final Spinner option = dialog.findViewById(R.id.spinner2);
        title.setText("Medellín");
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.location_array, R.layout.item_spinner);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);   //Set a default layout for items
        option.setAdapter(adapter);

        option.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(option.getSelectedItemId()!=0){
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
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void openPYPDialog(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_p_y_p);
        ImageView close = dialog.findViewById(R.id.close_dialog_steps);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void notificateTips(Context context, boolean checked) {
        Intent intent = new Intent(context, TipsReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(),
                100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        if (!checked) {

            alarmManager.cancel(pendingIntent);
            Toast.makeText(context, "Cancelado", Toast.LENGTH_SHORT).show();

        } else {

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 5);
            calendar.set(Calendar.SECOND, 0);

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(), AlarmManager.INTERVAL_HALF_HOUR, pendingIntent);

            Toast.makeText(context, "Programado", Toast.LENGTH_SHORT).show();
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

            Toast.makeText(c, "Programado recordatoria para las 6:00 AM", Toast.LENGTH_SHORT).show();
        } else {

            alarmManager.cancel(pendingIntent);

            Toast.makeText(c, "Cancelado", Toast.LENGTH_SHORT).show();

        }


    }

}
