package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller.TrafficFineAdapter;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB.TrafficFine;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrafficFineFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<TrafficFine> trafficFines;

    public TrafficFineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_traffic_fine, container, false);

        mRecyclerView = getActivity().findViewById(R.id.rv_content_traffic_fine);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new TrafficFineAdapter(trafficFines);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

}
