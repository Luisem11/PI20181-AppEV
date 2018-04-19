package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB.DbHelper;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.DB.StatusContract;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.View.MainActivity.RCDetailsActivity;


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
        Cursor cursor = dbHelper.getTypeById(getId(position));

        if(cursor.moveToFirst()) {
            do {
                if (cursor.getString(0).equals("TM")) {
                    holder.cardView1.setVisibility(View.VISIBLE);
                }
                if (cursor.getString(0).equals("N")) {
                    holder.cardView2.setVisibility(View.VISIBLE);
                }
                if (cursor.getString(0).equals("IM")) {
                    holder.cardView3.setVisibility(View.VISIBLE);
                }
                if (cursor.getString(0).equals("IF")) {
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
        public TextView personName;
        public TextView personAge;
        public LinearLayout typeLinearLayout;
        CardView cardView1, cardView2, cardView3, cardView4;

        public TipsViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            personName = (TextView) itemView.findViewById(R.id.title);
            personAge = (TextView) itemView.findViewById(R.id.subtitle);
            typeLinearLayout = (LinearLayout) itemView.findViewById(R.id.type);
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
