package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.Preferences;

import android.os.Bundle;
import android.support.v7.preference.DialogPreference;
import android.support.v7.preference.PreferenceDialogFragmentCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;

/**
 * The Dialog for the {@link LocationPreference}.
 *
 * @author Jakob Ulbrich
 */
public class LocationPreferenceDialogFragmentCompat extends PreferenceDialogFragmentCompat {

    /**
     * The TimePicker widget
     */
    private TextView mTimePicker;
    private Spinner spinner;

    /**
     * Creates a new Instance of the TimePreferenceDialogFragment and stores the key of the
     * related Preference
     *
     * @param key The key of the related Preference
     * @return A new Instance of the TimePreferenceDialogFragment
     */
    public static LocationPreferenceDialogFragmentCompat newInstance(String key) {
        final LocationPreferenceDialogFragmentCompat
                fragment = new LocationPreferenceDialogFragmentCompat();
        final Bundle b = new Bundle(1);
        b.putString(ARG_KEY, key);
        fragment.setArguments(b);

        return fragment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);

        spinner = (Spinner) view.findViewById(R.id.spinner2);
        mTimePicker = (TextView) view.findViewById(R.id.edit);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.location_array, R.layout.item_spinner);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);   //Set a default layout for items
        spinner.setAdapter(adapter);

        mTimePicker = (TextView) view.findViewById(R.id.edit);

        // Exception: There is no TimePicker with the id 'edit' in the dialog.
        if (mTimePicker == null) {
            throw new IllegalStateException("Dialog view must contain a TimePicker with id 'edit'");
        }

        // Get the time from the related Preference
        String minutesAfterMidnight = null;
        DialogPreference preference = getPreference();
        if (preference instanceof LocationPreference) {
            minutesAfterMidnight = ((LocationPreference) preference).getCity();
        }

        // Set the time to the TimePicker
        if (minutesAfterMidnight != null) {

            mTimePicker.setText(minutesAfterMidnight);
        }
        final String finalMinutesAfterMidnight = minutesAfterMidnight;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(spinner.getSelectedItemId()!=0){
                    mTimePicker.setText(spinner.getSelectedItem().toString());
                }else
                {
                    mTimePicker.setText(finalMinutesAfterMidnight);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {

            // Generate value to save
            String minutesAfterMidnight =  spinner.getSelectedItem().toString();

            // Save the value
            DialogPreference preference = getPreference();
            if (preference instanceof LocationPreference && !minutesAfterMidnight.equals("")) {
                LocationPreference locationPreference = ((LocationPreference) preference);
                // This allows the client to ignore the user value.
                if (locationPreference.callChangeListener(minutesAfterMidnight)) {
                    // Save the value
                    locationPreference.setTime(minutesAfterMidnight);
                }
            }
        }
    }
}
