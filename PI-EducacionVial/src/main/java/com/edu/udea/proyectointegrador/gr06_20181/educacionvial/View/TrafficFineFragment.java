package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller.TrafficFineAdapter;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB.TrafficFine;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrafficFineFragment extends Fragment implements View.OnClickListener{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<TrafficFine> trafficFines;
    private CardView frecuentTrafficFine, trafficFineGotten;

    public TrafficFineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_traffic_fine, container, false);

        trafficFines = new ArrayList<>();
        trafficFines = showTrafficFine((ArrayList) trafficFines);

        frecuentTrafficFine = view.findViewById(R.id.card_view_frecuent_trafficfine);
        trafficFineGotten = view.findViewById(R.id.traffic_fine_frequent_card_detail);
        frecuentTrafficFine.setOnClickListener(this);
        trafficFineGotten.setOnClickListener(this);


        mRecyclerView = view.findViewById(R.id.rv_content_traffic_fine);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getContext(),4);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)


        mAdapter = new TrafficFineAdapter(trafficFines);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }



    @Override
    public void onClick(View view) {
        FragmentTransaction ft;

        switch (view.getId()){
            case R.id.card_view_frecuent_trafficfine:
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.help_container, new FrecuentTrafficFineFragment()).addToBackStack(null);
                ft.commit();
                break;
            case R.id.traffic_fine_frequent_card_detail:
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.help_container, new GottenTrafficFineFragment()).addToBackStack(null);
                ft.commit();
                break;
        }
    }



    public ArrayList showTrafficFine(ArrayList trafficFine){

        //Multas Categoria A
        trafficFine.add(new TrafficFine("A1","No transitar por la derecha de la vía","104.164","A"));
        trafficFine.add(new TrafficFine("A2","Agarrarse de otro vehículo en movimiento.","104.164","A"));
        trafficFine.add(new TrafficFine("A3","Transportar personas o cosas que disminuyan su visibilidad e incomoden la conducción","104.164","A"));
        trafficFine.add(new TrafficFine("A4","Transitar por andenes, aceras, puentes o de mas lugares de uso exclusivo para tránsito de peatones.","104.164","A"));
        trafficFine.add(new TrafficFine("A5","No respetar las señales de tránsito","104.164","A"));
        trafficFine.add(new TrafficFine("A6","Transitar sin los dispositivos luminosos requeridos","104.164","A"));
        trafficFine.add(new TrafficFine("A7","Transitar sin dispositivos que permitan la parada inmediata o con ellos, pero en estado defectuoso","104.164","A"));
        trafficFine.add(new TrafficFine("A8","Transitar por zonas prohibidas o, por aquellas vías en donde las autoridades competentes lo prohíban, conducir por vías diferentes a aquellas especialmente diseñadas para ello cuando las hubiere.","104.164","A"));
        trafficFine.add(new TrafficFine("A9","Adelantar entre dos (2) vehículos automotores que estén en sus respectivos carriles","104.164","A"));
        trafficFine.add(new TrafficFine("A10","Conducir por la vía férrea o por zonas de protección y seguridad.","104.164","A"));
        trafficFine.add(new TrafficFine("A11","Transitar por zonas restringidas o por vías de alta velocidad como autopistas y arterias, en este caso el vehículo automotor será inmovilizado","104.164","A"));
        trafficFine.add(new TrafficFine("A12","Prestar Servicio Público con este tipo de vehículos. Además, el vehículos será inmovilizado por primera vez, por el termino de 5 días, por segunda vez 20 días y por tercera vez 40 días","104.164","A"));

        //Multas Categoria B


        //Multas Categoria C
        trafficFine.add(new TrafficFine("C14","PICO Y PLACA","390000","C"));

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
