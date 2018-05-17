package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.MainActivity.Help;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.Toast;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller.AnimationUtils;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller.StepsAdapter;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.Step;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GottenTrafficFineFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Step> stepsAccidentList;
    private ImageView cv;
    private int count;



    public GottenTrafficFineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_gotten_traffic_fine, container, false);


        count = 0;

        stepsAccidentList = new ArrayList<>();
        stepsAccidentList = showTrafficFine((ArrayList) stepsAccidentList);

        mRecyclerView = view.findViewById(R.id.rv_content_steps_traffic_fine);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        cv = view.findViewById(R.id.cv_);
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count==8){
                    Toast.makeText(view.getContext(),"Hola Lis :B", Toast.LENGTH_LONG).show();

                    count=0;
                }
                count++;
            }
        });


        // specify an adapter (see also next example)


        mAdapter = new StepsAdapter(stepsAccidentList, getContext());
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    public ArrayList showTrafficFine(ArrayList stpsList){

        stpsList.add(new Step(R.drawable.ic_1, "Para esto, puedes consultar y pagar a través de una herramienta virtual conocida como SIMIT. Solo necesitas digitar el número de documento o el número de la placa","Verifica si tienes un comparendo","pag:https://consulta.simit.org.co/Simit/verificar/contenido_verificar_pago_linea.jsp","","trafficfine"));
        stpsList.add(new Step(R.drawable.ic_2, "El comparendo hace referencia a la citación por la formulación de cargos sobre la violación de una norma de tránsito.La multa es la sanción monetaria impuesta a los infractores que se refleja en el pago de salarios mínimos diarios legales vigentes","Diferencia entre Multa y Comparendo","","","trafficfine"));
        stpsList.add(new Step(R.drawable.ic_3, "Para esto, debes dirigirte ante la autoridad competente dentro de los siguientes 5 días hábiles después de recibir la notificación de la infracción","Objeta si no estás de acuerdo","null","null","trafficfine"));
        stpsList.add(new Step(R.drawable.ic_4, "Para esto dirígete a una sucursal de los siguientes bancos \n" + "•Banco de Occidente\n" + "•Banco caja social\n" + "Siempre y cuando se presente el recibo de pago y se realice en efectivo, se puede cancelar en los almacenes Éxito, Éxito Express, Carulla, Carulla Express y Surtimax. También te puedes dirigir a  cualquier oficina de AV Villas y Davivienda","Realiza el pago de la multa de tránsito","null","null","trafficfine"));
        stpsList.add(new Step(R.drawable.ic_5, "Si no realizas el pago a tiempo, la autoridad de tránsito tiene hasta seis meses para notificar al infractor sobre el cobro coactivo, lo cual implica el embargo de algunos de los bienes del infractor, problemas crediticios y perjuicios a su hoja de vida","No olvides pagar a tiempo! ","null","null","trafficfine"));


        return stpsList;
    }

}
