package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB.TrafficFine;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;

import java.util.List;

/**
 * Created by user on 20/04/2018.
 */

public class FrequentTrafficFineAdapter extends RecyclerView.Adapter<FrequentTrafficFineAdapter.ViewHolder> {

    public List<TrafficFine> trafficFine;

    public FrequentTrafficFineAdapter(List<TrafficFine> trafficFine) {
        this.trafficFine = trafficFine;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.frecuent_traffic_fine_card, parent, false);

        ViewHolder vh = new ViewHolder(v, trafficFine);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.trafficFineCode.setText(trafficFine.get(position).getCode());
        holder.trafficFinePrice.setText(trafficFine.get(position).getPrice());
        holder.frequentTrafficFineDescription.setText(trafficFine.get(position).getDescription());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return trafficFine.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public TextView trafficFineCode;
        public TextView trafficFinePrice, frequentTrafficFineDescription;
        public List<TrafficFine> trafficFine;


        // each data item is just a string in this case
        public ViewHolder(View itemView, List<TrafficFine> trafficFine) {
            super(itemView);
            cardView = itemView.findViewById(R.id.traffic_fine_frequent_card_detail);
            trafficFineCode = itemView.findViewById(R.id.code_detail);
            trafficFinePrice = itemView.findViewById(R.id.price_detail);
            frequentTrafficFineDescription = itemView.findViewById(R.id.description_detail);
            this.trafficFine = trafficFine;
        }
    }
}
