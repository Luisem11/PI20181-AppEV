package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.MainActivity.Help;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LayoutAnimationController;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller.DirectoryAdapter;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller.TipsAdapter;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.Dir;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * A simple {@link Fragment} subclass.
 */
public class HelpFragment extends Fragment implements DirectoryAdapter.OnItemClickListener {

    private RecyclerView directoryList;
    public DirectoryAdapter directoryAdapter;




    public HelpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_help, container, false);

        directoryList = (RecyclerView) view.findViewById(R.id.directory_recyclerview);
        directoryAdapter = new DirectoryAdapter(null, this);




        LayoutAnimationController controller =
                android.view.animation.AnimationUtils
                        .loadLayoutAnimation(this.getContext(), R.anim.layout_side_from_bottom);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity().getApplication().getApplicationContext());
        directoryList.setLayoutManager(linearLayoutManager);
        directoryList.setAdapter(directoryAdapter);
        directoryList.setLayoutAnimation(controller);
        directoryList.setHasFixedSize(true);
        loadDirectories();



        return view;
    }

    private void loadDirectories() {

        List<Dir> listDirections = new ArrayList<>();
        listDirections.add(new Dir("Bomberos","tel:119","Call","Llamar a los bomberos"));
        listDirections.add(new Dir("Tránsito","tel:125","Call","Llamar a el transito"));
        listDirections.add(new Dir("Policía","tel:112","Call","Llamar a Policía"));
        listDirections.add(new Dir("Cruz Roja","tel:(+57) 232 0053","Call","Llamar a la Cruz Roja"));
        listDirections.add(new Dir("Policía de Carretera","tel:767","Call","Llamar a Policía de Carretera"));
        //listDirections.add(new Dir("","","","Llamar a  "));
        directoryAdapter.swapCursor(listDirections);
    }

    @Override
    public void onClick(DirectoryAdapter.DirectoryViewHolder holder, String numberDirectory) {

        Intent intent = new Intent(Intent.ACTION_DIAL,
                Uri.parse(numberDirectory));
        startActivity(intent);
    }
}
