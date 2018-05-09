package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.MainActivity.Help;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LayoutAnimationController;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller.FrequentTrafficFineAdapter;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller.StepsAccidentAdapter;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB.TrafficFine;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.Step;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccidentsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Step> stepsAccidentList;



    public AccidentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_accidents, container, false);

        stepsAccidentList = new ArrayList<>();
        stepsAccidentList = showTrafficFine((ArrayList) stepsAccidentList);

        mRecyclerView = view.findViewById(R.id.rv_content_steps_accident);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        LayoutAnimationController controller =
                android.view.animation.AnimationUtils.
                        loadLayoutAnimation(this.getContext(), R.anim.layout_side_from_rigth);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setLayoutAnimation(controller);


        // specify an adapter (see also next example)


        mAdapter = new StepsAccidentAdapter(stepsAccidentList);
        mRecyclerView.setAdapter(mAdapter);

        return view;

    }


    public ArrayList showTrafficFine(ArrayList stpsList){

        stpsList.add(new Step(R.drawable.ic_1,"Llama siempre a las autoridades de tránsito."));
        stpsList.add(new Step(R.drawable.ic_2,"Toma fotografías de lo ocurrido."));
        stpsList.add(new Step(R.drawable.ic_3,"No muevas tu vehículo del lugar del accidente."));
        stpsList.add(new Step(R.drawable.ic_4,"Busca personas que presenciaron el accidente."));
        stpsList.add(new Step(R.drawable.ic_5,"Ten a la mano tus documentos."));
        stpsList.add(new Step(R.drawable.ic_6,"Ten presente la fecha de la audiencia en el Tránsito."));

        return stpsList;
    }

}
