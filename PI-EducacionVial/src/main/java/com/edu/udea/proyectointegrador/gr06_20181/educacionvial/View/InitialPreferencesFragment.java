package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class InitialPreferencesFragment extends Fragment {


    public InitialPreferencesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_initial_preferences, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();

        return view;
    }



}
