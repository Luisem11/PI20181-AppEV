package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB.StatusContract;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;


public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.TipsViewHolder> {
    private Cursor items;

    public TipsAdapter(Cursor items) {
        this.items = items;
    }

    @Override
    public TipsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tip, parent, false);
        TipsViewHolder tvh = new TipsViewHolder(view);
        return tvh;
    }

    @Override
    public void onBindViewHolder(TipsViewHolder holder, int position) {

        items.moveToPosition(position);

        String s = items.getString(items.getColumnIndex(StatusContract.Column_Tip.TITLE));
        holder.personName.setText(s);
        s = items.getString(items.getColumnIndex(StatusContract.Column_Tip.SUBTITLE));
        holder.personAge.setText(s);


    }

    @Override
    public int getItemCount() {
        if (items!= null){
            return items.getCount();
        }
        return 0;
    }

    public void swapCursor(Cursor cursor) {
        if (cursor != null) {
            items = cursor;
            notifyDataSetChanged();
        }
    }

    public static class TipsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView personName;
        public TextView personAge;
        public ImageView personPhoto;

        public TipsViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            personName = (TextView) itemView.findViewById(R.id.title);
            personAge = (TextView) itemView.findViewById(R.id.subtitle);

        }

        @Override
        public void onClick(View view) {

        }
    }
    }
