package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
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

import me.toptas.fancyshowcase.FancyShowCaseQueue;
import me.toptas.fancyshowcase.FancyShowCaseView;
import me.toptas.fancyshowcase.FocusShape;
import me.toptas.fancyshowcase.OnCompleteListener;
import me.toptas.fancyshowcase.OnViewInflateListener;


public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.TipsViewHolder> {
    private Cursor items, types;
    private OnItemClickListener escucha;
    private DbHelper dbHelper;
    private Activity activity;
    private Boolean show;
    private FancyShowCaseView mFancyView;
    private FancyShowCaseQueue mQueue;
    private View fab;


    public TipsAdapter(Cursor items, OnItemClickListener escucha, Activity activity1) {
        this.items = items;
        this.escucha = escucha;
        this.activity = activity1;
        show=false;
    }

    public TipsAdapter(Cursor cursor, OnItemClickListener listener, Activity activity, boolean b, View fab) {
        this.fab = fab;
        this.items = items;
        this.escucha = escucha;
        this.activity = activity;
        this.show = b;
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

        if (position==0&&show){

            Showcase(holder);


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
        CardView cardView1, cardView2, cardView3, cardView4, cardView;
        Activity activity;

        public TipsViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            titleTextView = (TextView) itemView.findViewById(R.id.title);
            subtitleTextView = (TextView) itemView.findViewById(R.id.subtitle);
            typeLinearLayout = (LinearLayout) itemView.findViewById(R.id.type);
            subtitleTextView.setVisibility(View.GONE);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
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

    private void Showcase(TipsViewHolder holder) {

        mQueue = new FancyShowCaseQueue();

        mFancyView = new FancyShowCaseView.Builder(activity)
                .customView(R.layout.showcase_3, new OnViewInflateListener() {
                    @Override
                    public void onViewInflated(View view) {
                        view.findViewById(R.id.showcase_close)
                                .setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        mQueue.cancel(true);
                                    }
                                });

                    }
                })
                .delay(1000)
                .focusOn(holder.cardView)
                .focusShape(FocusShape.ROUNDED_RECTANGLE)
                .roundRectRadius(90)
                .closeOnTouch(true)
                .build();
        mQueue.add(mFancyView);

        mFancyView = new FancyShowCaseView.Builder(activity)
                .focusOn(fab)
                .customView(R.layout.showcase_4, new OnViewInflateListener() {
                    @Override
                    public void onViewInflated(View view) {
                        view.findViewById(R.id.showcase_close)
                                .setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        mQueue.cancel(true);
                                    }
                                });

                    }
                })
                .closeOnTouch(true)
                .build();
        mQueue.add(mFancyView);

        mFancyView = new FancyShowCaseView.Builder(activity)
                .focusOn(holder.typeLinearLayout)
                .focusShape(FocusShape.ROUNDED_RECTANGLE)
                .roundRectRadius(60)
                .customView(R.layout.showcase_5, new OnViewInflateListener() {
                    @Override
                    public void onViewInflated(View view) {
                        view.findViewById(R.id.showcase_close)
                                .setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        mQueue.cancel(true);
                                    }
                                });

                    }
                })
                .closeOnTouch(true)
                .build();
        mQueue.add(mFancyView);

        mQueue.show();
        mQueue.setCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete() {
                Intent intent = new Intent();
                intent.putExtra("SC_RCA", true);
                activity.setResult(1001, intent);
                activity.finish();
            }
        });
    }



}
