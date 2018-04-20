package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller.FrequentTrafficFineAdapter;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller.TrafficFineAdapter;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB.TrafficFine;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FrecuentTrafficFineFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<TrafficFine> trafficFines;

    public FrecuentTrafficFineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frecuent_traffic_fine, container, false);

        trafficFines = new ArrayList<>();
        trafficFines = showTrafficFine((ArrayList) trafficFines);

        mRecyclerView = view.findViewById(R.id.rv_content_frequent_traffic_fine);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)


        mAdapter = new FrequentTrafficFineAdapter(trafficFines);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    public ArrayList showTrafficFine(ArrayList trafficFine){

        //Multas Categoria A
        trafficFine.add(new TrafficFine("A2","Agarrarse de otro vehículo en movimiento.","104.164","A"));
        trafficFine.add(new TrafficFine("A3","Transportar personas o cosas que disminuyan su visibilidad e incomoden la conducción","104.164","A"));
        trafficFine.add(new TrafficFine("A4","Transitar por andenes, aceras, puentes o de mas lugares de uso exclusivo para tránsito de peatones.","104.164","A"));
        trafficFine.add(new TrafficFine("A5","No respetar las señales de tránsito","104.164","A"));
        trafficFine.add(new TrafficFine("A9","Adelantar entre dos (2) vehículos automotores que estén en sus respectivos carriles","104.164","A"));
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
