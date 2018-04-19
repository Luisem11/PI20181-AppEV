package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.util.Log;
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
 * Created by user on 16/04/2018.
 */

public class TrafficFineAdapter extends RecyclerView.Adapter<TrafficFineAdapter.ViewHolder> {

    public List<TrafficFine> trafficFine;

    public TrafficFineAdapter(List<TrafficFine> trafficFine) {
        this.trafficFine = trafficFine;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TrafficFineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trafficfine_card, parent, false);

        ViewHolder vh = new ViewHolder(v,trafficFine);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.trafficFineCode.setText(trafficFine.get(position).getCode());
        holder.trafficFinePrice.setText(trafficFine.get(position).getPrice());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return trafficFine.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CardView cardView;
        public TextView trafficFineCode;
        public TextView trafficFinePrice;
        public List<TrafficFine> trafficFine;


        // each data item is just a string in this case
        public ViewHolder(View itemView, List<TrafficFine> trafficFine) {
            super(itemView);
            itemView.setOnClickListener(this);
            cardView =  itemView.findViewById(R.id.card_view_trafficfine);
            trafficFineCode = itemView.findViewById(R.id.traffic_fine_code_card);
            trafficFinePrice = itemView.findViewById(R.id.traffic_fine_price_card);
            this.trafficFine = trafficFine;
        }

        @Override
        public void onClick(View view) {
            showPickerDialog(view);
        }

        private void showPickerDialog(View view) {
            Context context = view.getContext();
            int pos = getAdapterPosition();
            TextView priceLoad,descriptionLoad;
            LayoutInflater l = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View v = l.inflate(R.layout.detail_trafficfine_dialog, null);
            final FrameLayout layout = new FrameLayout(context);
            layout.addView(v, new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER));

            priceLoad = v.findViewById(R.id.traffic_fine_price_dialog);
            priceLoad.setText(String.valueOf(trafficFine.get(pos).getPrice()));
            descriptionLoad = v.findViewById(R.id.traffic_fine_description_dialog);
            descriptionLoad.setText(String.valueOf(trafficFine.get(pos).getDescription()));
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle(trafficFine.get(pos).getCode());
            alertDialogBuilder.setView(layout);
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {

                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

}
