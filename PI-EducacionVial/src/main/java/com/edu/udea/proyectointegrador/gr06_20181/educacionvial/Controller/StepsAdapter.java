package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.Step;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;

import java.util.List;

/**
 * Created by user on 20/04/2018.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {

    public List<Step> stepList;
    private Context context;


    public StepsAdapter(List<Step> stepList, Context context) {
        this.stepList = stepList;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_steps, parent, false);

        ViewHolder vh = new ViewHolder(v, stepList);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.stepsImageView.setImageResource(stepList.get(position).getNumber());
        holder.descriptionTextView.setText(stepList.get(position).getTitle());
        if (stepList.get(position).getType().equals("trafficfine")) {
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.red));
            holder.stepsImageView.setColorFilter(context.getResources().getColor(R.color.colorWhite));
            holder.descriptionTextView.setTextColor(context.getResources().getColor(R.color.colorWhite));
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(view.getContext());
                dialog.setContentView(R.layout.dialog_detail_steps_accident);
                CardView card = dialog.findViewById(R.id.card_dialog);
                ImageView icon = dialog.findViewById(R.id.icon_dialog_steps);
                ImageView close = dialog.findViewById(R.id.close_dialog_steps);
                TextView title = dialog.findViewById(R.id.title_dialog_steps);
                TextView body = dialog.findViewById(R.id.body_dialog_steps);
                if (stepList.get(position).getType().equals("trafficfine")) {
                    card.setCardBackgroundColor(context.getResources().getColor(R.color.red));
                    icon.setColorFilter(context.getResources().getColor(R.color.colorWhite));
                    close.setColorFilter(context.getResources().getColor(R.color.colorWhite));
                    title.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    body.setTextColor(context.getResources().getColor(R.color.colorWhite));

                }


                icon.setImageResource(stepList.get(position).getNumber());
                body.setText(stepList.get(position).getDescription());
                title.setText(stepList.get(position).getTitle());

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.show();
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return stepList.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public ImageView stepsImageView;
        public TextView descriptionTextView;
        public List<Step> steps;


        // each data item is just a string in this case
        public ViewHolder(View itemView, List<Step> steps) {
            super(itemView);
            cardView = itemView.findViewById(R.id.steps_card_view);
            stepsImageView = itemView.findViewById(R.id.image_steps);
            descriptionTextView = itemView.findViewById(R.id.steps_description);

            this.steps = steps;
        }
    }
}