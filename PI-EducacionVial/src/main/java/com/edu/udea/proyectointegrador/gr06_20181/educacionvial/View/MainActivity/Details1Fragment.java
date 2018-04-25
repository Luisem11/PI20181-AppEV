package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.MainActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        body2TextView = (TextView) view.findViewById(R.id.body2);

        Button button = view.findViewById(R.id.action);
        StringBuilder buttonText = new StringBuilder();
        final StringBuilder link = new StringBuilder();
        String action = tip.getLink();
        Character a;
        Boolean b= true;
        for (int i = 0; i<action.length(); i++){
            a = action.charAt(i);
            if (a.equals(':')&&b){
                b = false;
            }else{
                if (b){

                    buttonText.append("").append(a);
                }else {
                    link.append("").append(a);
                }
            }

        }
        button.setText(buttonText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                switch (link.charAt(0)){
                    case '1':
                        intent = new Intent(view.getContext(), HelpActivity.class);
                        break;
                    case '2':
                        intent = new Intent(view.getContext(), MainActivity.class);
                        break;
                    default:
                        intent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse(""+link));
                        break;


                }

                startActivity(intent);
            }
        });
        title2TextView.setText(tip.getTitle2());
        body2TextView.setText(tip.getBody2());

        return view;
    }

}
