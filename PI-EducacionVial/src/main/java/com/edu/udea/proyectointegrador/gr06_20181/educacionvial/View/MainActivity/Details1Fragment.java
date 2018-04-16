package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.MainActivity;


import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB.Tip;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Details1Fragment extends Fragment {

    private TextView titleTextView, title2TextView, body1TextView, body2TextView;
    private Tip tip;



    public Details1Fragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public Details1Fragment(Tip c) {
        tip = c;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_details1, container, false);

        title2TextView = (TextView) view.findViewById(R.id.title2);
        body1TextView = (TextView) view.findViewById(R.id.body1);
        body2TextView = (TextView) view.findViewById(R.id.body2);

        title2TextView.setText(tip.getTitle2());
        body1TextView.setText(tip.getBody());
        body2TextView.setText(tip.getBody2());

        return view;
    }

}
