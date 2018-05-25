package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller;

import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB.DbHelper;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB.StatusContract;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;


public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.TipsViewHolder> {
    private Cursor items, types;
    private OnItemClickListener escucha;
    private DbHelper dbHelper;

    public TipsAdapter(Cursor items, OnItemClickListener escucha) {
        this.items = items;
        this.escucha = escucha;
    }

    public interface OnItemClickListener {
        void onClick(TipsViewHolder holder, int idTip);
    }


    @Override
    public TipsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tip, parent, false);
        TipsViewHolder tvh = new TipsViewHolder(view);
        return tvh;
    }

    @Override
    public void onBindViewHolder(TipsViewHolder holder, int position) {


        holder.cardView1.setVisibility(View.GONE);
        holder.cardView2.setVisibility(View.GONE);
        holder.cardView3.setVisibility(View.GONE);
        holder.cardView4.setVisibility(View.GONE);

        items.moveToPosition(position);
        String s = items.getString(items.getColumnIndex(StatusContract.Column_Tip.TITLE));
        holder.titleTextView.setText(s);
        s = items.getString(items.getColumnIndex(StatusContract.Column_Tip.SUBTITLE));
        holder.subtitleTextView.setText(s);
        Cursor cursor = dbHelper.getTypeById(items.getInt(items.getColumnIndex(StatusContract.Column_Tip.ID)));

        if(cursor.moveToFirst()) {
            do {
                if (cursor.getInt(cursor.getColumnIndex(StatusContract.Column_Type_Tip.ID_TYPE))==1) {
                    holder.cardView1.setVisibility(View.VISIBLE);
                }
                if (cursor.getInt(cursor.getColumnIndex(StatusContract.Column_Type_Tip.ID_TYPE))==2) {
                    holder.cardView2.setVisibility(View.VISIBLE);
                }
                if (cursor.getInt(cursor.getColumnIndex(StatusContract.Column_Type_Tip.ID_TYPE))==3) {
                    holder.cardView3.setVisibility(View.VISIBLE);
                }
                if (cursor.getInt(cursor.getColumnIndex(StatusContract.Column_Type_Tip.ID_TYPE))==4) {
                    holder.cardView4.setVisibility(View.VISIBLE);
                }
            } while (cursor.moveToNext());
        }

    }

    @Override
    public int getItemCount() {
        if (items!= null){
            return items.getCount();
        }
        return 0;
    }

    public void swapCursor(Cursor cursor, DbHelper db) {
        if (cursor != null) {
            items = cursor;
            dbHelper = db;

            notifyDataSetChanged();

        }
    }

    public class TipsViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public TextView titleTextView;
        public TextView subtitleTextView;
        public LinearLayout typeLinearLayout;
        CardView cardView1, cardView2, cardView3, cardView4;

        public TipsViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            titleTextView = (TextView) itemView.findViewById(R.id.title);
            subtitleTextView = (TextView) itemView.findViewById(R.id.subtitle);
            typeLinearLayout = (LinearLayout) itemView.findViewById(R.id.type);
            subtitleTextView.setVisibility(View.GONE);
            cardView1 = (CardView) itemView.findViewById(R.id.card1);
            cardView2 = (CardView) itemView.findViewById(R.id.card2);
            cardView3 = (CardView) itemView.findViewById(R.id.card3);
            cardView4 = (CardView) itemView.findViewById(R.id.card4);

        }

        @Override
        public void onClick(View view) {
            escucha.onClick(this, getId(getAdapterPosition()));

        }


    }

    private int getId(int position) {
        if (items != null) {
            if (items.moveToPosition(position)) {
                return items.getInt(items.getColumnIndex(StatusContract.Column_Tip.ID));
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    }
