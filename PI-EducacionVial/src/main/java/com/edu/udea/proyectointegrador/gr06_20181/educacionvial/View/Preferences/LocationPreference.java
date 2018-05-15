package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.Preferences;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.preference.DialogPreference;
import android.util.AttributeSet;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;

/**
 * A Preference to select a specific Time with a {@link android.widget.TimePicker}.
 *
 * @author Jakob Ulbrich
 */
public class LocationPreference extends DialogPreference {

    /**
     * In Minutes after midnight
     */
    private String mCity;

    /**
     * Resource of the dialog layout
     */
    private int mDialogLayoutResId = R.layout.dialog_location_preference;

    public LocationPreference(Context context) {
        // Delegate to other constructor
        this(context, null);
    }

    public LocationPreference(Context context, AttributeSet attrs) {
        // Delegate to other constructor
        // Use the preferenceStyle as the default style
        this(context, attrs, R.attr.preferenceStyle);
    }

    public LocationPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        // Delegate to other constructor
        this(context, attrs, defStyleAttr, defStyleAttr);
    }

    public LocationPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        // Du custom stuff here
        // ...
        // read attributes etc.
    }

    /**
     * Gets the time from the Shared Preferences
     *
     * @return The current preference value
     */
    public String getCity() {
        return mCity;
    }

    /**
     * Saves the time to the SharedPreferences
     *
     * @param time The time to save
     */
    public void setTime(String time) {
        mCity = time;

        // Save to SharedPreference
        persistString(time);
    }

    //

    /**
     * Called when a Preference is being inflated and the default value attribute needs to be read
     */
    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        // The type of this preference is Int, so we read the default value from the attributes
        // as Int. Fallback value is set to 0.
        return a.getString(index);
    }

    //

    /**
     * Returns the layout resource that is used as the content View for the dialog
     */
    @Override
    public int getDialogLayoutResource() {
        return mDialogLayoutResId;
    }


    //

    /**
     * Implement this to set the initial value of the Preference.
     */
    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        // If the value can be restored, do it. If not, use the default value.
        setTime(restorePersistedValue ?
                getPersistedString(mCity) : (String) defaultValue);
    }

}
