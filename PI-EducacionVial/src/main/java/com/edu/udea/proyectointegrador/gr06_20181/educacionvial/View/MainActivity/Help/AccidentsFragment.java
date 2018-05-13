package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.MainActivity.Help;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LayoutAnimationController;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller.StepsAccidentAdapter;
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

        stpsList.add(new Step(R.drawable.ic_1, "Llama inmediatamente a las autoridades que pueden ayudarte. Línea de Emergencias, si es del caso, a la Policía de Carreteras y a tu aseguradora. Durante la conversación, describe detalladamente qué pasó en el accidente de tránsito y la ubicación exacta donde sucedió", "Llama siempre a las autoridades de tránsito", "tel:123", "Llama a Emergencias"));
        stpsList.add(new Step(R.drawable.ic_2, "Es útil para tener evidencia de lo sucedido, para saber quién tuvo la culpa y cuantos vehículos estuvieron involucrados en el accidente", "Toma fotografías de lo ocurrido", "null", "null"));
        stpsList.add(new Step(R.drawable.ic_3, "Es la única manera que tienen las autoridades de tránsito de saber lo que    realmente ocurrió. Espera a que las autoridades lleguen al sitio del accidente", "No muevas tu vehículo del lugar del accidente", "null", "null"));
        stpsList.add(new Step(R.drawable.ic_4, "Los testigos son útiles para ayudar a determinar cuál fue la causa real del accidente porque puede que ninguno de los involucrados haya tenido la culpa", "Busca personas que presenciaron el accidente", "null", "null"));
        stpsList.add(new Step(R.drawable.ic_5, "• Cédula\n• Licencia de conducción\n• Matrícula del auto\n• SOAT\n• Revisión tecnico-mecánica\n", "Ten a la mano tus documentos", "null", "null"));
        stpsList.add(new Step(R.drawable.ic_6, "Si el presunto infractor no comparece en el término de tres días hábiles, la multa que se impone se incrementa hasta en un 100 por ciento debido a la ausencia de la persona en ese término", "Ten presente la fecha de la audiencia en el Tránsito", "null", "null"));

        return stpsList;
    }

}
