package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccidentsFragment extends Fragment {


    public AccidentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_accidents, container, false);
    }

}
