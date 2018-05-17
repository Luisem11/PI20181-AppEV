package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.MainActivity.Help;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller.AnimationUtils;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB.TrafficFine;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrafficFineFragment extends Fragment implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<TrafficFine> trafficFines;
    private CardView stepsTrafficFine, knowTrafficFine, mTrafficFine;
    private LinearLayout layoutContent;

    public TrafficFineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_traffic_fine, container, false);
//
//        trafficFines = new ArrayList<>();
//        trafficFines = showTrafficFine((ArrayList) trafficFines);

        layoutContent = view.findViewById(R.id.traffic_fine_layout);
        stepsTrafficFine = view.findViewById(R.id.action1);
        knowTrafficFine = view.findViewById(R.id.action2);
        mTrafficFine = view.findViewById(R.id.action3);
        stepsTrafficFine.setOnClickListener(this);
        knowTrafficFine.setOnClickListener(this);
        mTrafficFine.setOnClickListener(this);

//
//        mRecyclerView = view.findViewById(R.id.rv_content_traffic_fine);
//        mRecyclerView.setHasFixedSize(true);
//        mLayoutManager = new GridLayoutManager(getContext(),4);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//
//        // specify an adapter (see also next example)
//
//
//        mAdapter = new TrafficFineAdapter(trafficFines);
//        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction ft;

        View view1 = view.getRootView().findViewById(R.id.help_container);
        switch (view.getId()) {


            case R.id.action1:

                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.help_container, new GottenTrafficFineFragment()).addToBackStack(null);
                ft.commit();
                AnimationUtils.circularReveal(view1, stepsTrafficFine, 600);

                break;
            case R.id.action2:
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.help_container, new FrecuentTrafficFineFragment()).addToBackStack(null);
                ft.commit();
                AnimationUtils.circularReveal(view1, knowTrafficFine, 600);
                break;
            case R.id.action3:

                final Dialog dialog = new Dialog(view.getContext());
                dialog.setContentView(R.layout.dialog_detail_steps_accident);
                CardView card = dialog.findViewById(R.id.card_dialog);
                ImageView icon = dialog.findViewById(R.id.icon_dialog_steps);
                ImageView close = dialog.findViewById(R.id.close_dialog_steps);
                TextView title = dialog.findViewById(R.id.title_dialog_steps);
                TextView body = dialog.findViewById(R.id.body_dialog_steps);
                Button action = dialog.findViewById(R.id.action_dialog_steps);
                card.setCardBackgroundColor(getResources().getColor(R.color.red));
                icon.setColorFilter(null);
                close.setColorFilter(getResources().getColor(R.color.colorWhite));
                title.setTextColor(getResources().getColor(R.color.colorWhite));
                body.setTextColor(getResources().getColor(R.color.colorWhite));
                action.setText("Consultar");
                action.setTextColor(getResources().getColor(R.color.red));
                action.setVisibility(View.VISIBLE);
                action.setBackground(getResources().getDrawable(R.drawable.button_round_red));
                action.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://consulta.simit.org.co/Simit/verificar/contenido_verificar_pago_linea.jsp"));
                        startActivity(intent);

                    }
                });
                icon.setImageResource(R.drawable.ic_document);
                body.setText("Puedes consultar y pagar a través de una herramienta virtual conocida como SIMIT. \n" +
                        "Solo necesitas digitar el número de documento o el número de la placa");
                title.setText(R.string.m_3);

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.show();

                break;
        }
    }


    public ArrayList showTrafficFine(ArrayList trafficFine) {

        //Multas Categoria A
        trafficFine.add(new TrafficFine("A1", "No transitar por la derecha de la vía", "104.164", "A"));
        trafficFine.add(new TrafficFine("A2", "Agarrarse de otro vehículo en movimiento.", "104.164", "A"));
        trafficFine.add(new TrafficFine("A3", "Transportar personas o cosas que disminuyan su visibilidad e incomoden la conducción", "104.164", "A"));
        trafficFine.add(new TrafficFine("A4", "Transitar por andenes, aceras, puentes o de mas lugares de uso exclusivo para tránsito de peatones.", "104.164", "A"));
        trafficFine.add(new TrafficFine("A5", "No respetar las señales de tránsito", "104.164", "A"));
        trafficFine.add(new TrafficFine("A6", "Transitar sin los dispositivos luminosos requeridos", "104.164", "A"));
        trafficFine.add(new TrafficFine("A7", "Transitar sin dispositivos que permitan la parada inmediata o con ellos, pero en estado defectuoso", "104.164", "A"));
        trafficFine.add(new TrafficFine("A8", "Transitar por zonas prohibidas o, por aquellas vías en donde las autoridades competentes lo prohíban, conducir por vías diferentes a aquellas especialmente diseñadas para ello cuando las hubiere.", "104.164", "A"));
        trafficFine.add(new TrafficFine("A9", "Adelantar entre dos (2) vehículos automotores que estén en sus respectivos carriles", "104.164", "A"));
        trafficFine.add(new TrafficFine("A10", "Conducir por la vía férrea o por zonas de protección y seguridad.", "104.164", "A"));
        trafficFine.add(new TrafficFine("A11", "Transitar por zonas restringidas o por vías de alta velocidad como autopistas y arterias, en este caso el vehículo automotor será inmovilizado", "104.164", "A"));
        trafficFine.add(new TrafficFine("A12", "Prestar Servicio Público con este tipo de vehículos. Además, el vehículos será inmovilizado por primera vez, por el termino de 5 días, por segunda vez 20 días y por tercera vez 40 días", "104.164", "A"));

        //Multas Categoria B


        //Multas Categoria C
        trafficFine.add(new TrafficFine("C14", "PICO Y PLACA", "390000", "C"));

        //Multas Categoria D


        //Multas Categoria E


        //Multas Categoria F


        //Multas Categoria G


        //Multas Categoria H


        //Multas Categoria I


        //Multas Categoria J

        return trafficFine;
    }
}
