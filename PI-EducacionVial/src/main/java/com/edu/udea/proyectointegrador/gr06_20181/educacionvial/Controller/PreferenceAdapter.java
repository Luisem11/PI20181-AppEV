package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.Preference;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;

import java.util.List;

/**
 * Created by luisernesto on 3/05/18.
 */

public class PreferenceAdapter
        extends RecyclerView.Adapter<PreferenceAdapter.PreferenceViewHolder> {

    private List<Preference> items;
    public Preference preference;
    private SharedPreferences pref;
    private PreferenceAdapter.OnItemClickListener escucha;


    public void swapCursor(List<Preference> listDirections) {
        items = listDirections;
        notifyDataSetChanged();

    }

    public PreferenceAdapter(List<Preference> listDirections,  OnItemClickListener listener) {
        escucha=listener;
        items = listDirections;
    }

    public interface OnItemClickListener {
        void onClick(PreferenceAdapter.PreferenceViewHolder holder, int id, View view, Preference preference);
    }


    @Override
    public PreferenceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_preference, parent, false);
        PreferenceAdapter.PreferenceViewHolder tvh = new PreferenceAdapter.PreferenceViewHolder(view);
        return tvh;
    }

    @Override
    public void onBindViewHolder(PreferenceAdapter.PreferenceViewHolder holder, int position) {


        // Get the data item for this position
        preference = items.get(position);



        Boolean weatherOn = pref.getBoolean("weather",true);
        Boolean pypOn = pref.getBoolean("pyp",false);
        Boolean showcaseOn = pref.getBoolean("showcase",false);



        holder.preferenceTitle.setText(preference.getTitle());
        holder.preferenceDescription.setText(preference.getDescription());
        if (preference.getType().equals("check")) {
            holder.preferenceCheckBox.setVisibility(View.VISIBLE);
        }else {
            holder.preferenceCheckBox.setVisibility(View.GONE);
        }

        switch (preference.getTitle()) {
            case "Pico y Placa":
                holder.preferenceCheckBox.setChecked(pypOn);
                break;

            case "Clima":
                holder.preferenceCheckBox.setChecked(weatherOn);
                break;

            case "Modo Tutorial":
                holder.preferenceCheckBox.setChecked(showcaseOn);
                break;
        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class PreferenceViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        public LinearLayout preferenceLinearLayout;
        public TextView preferenceTitle, preferenceDescription;
        public CheckBox preferenceCheckBox;
        public View item;


        public PreferenceViewHolder(View itemView) {
            super(itemView);

            preferenceTitle = (TextView) itemView.findViewById(R.id.pref_title);
            preferenceDescription = (TextView) itemView.findViewById(R.id.pref_description);
            preferenceCheckBox = (CheckBox) itemView.findViewById(R.id.pref_checkbox);
            preferenceLinearLayout = itemView.findViewById(R.id.preference);
            preferenceLinearLayout.setOnClickListener(this);
            pref = itemView.getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);




        }

        @Override
        public void onClick(View view) {
            escucha.onClick(this, getAdapterPosition(), preferenceLinearLayout, items.get(getAdapterPosition()));

        }
    }

}
