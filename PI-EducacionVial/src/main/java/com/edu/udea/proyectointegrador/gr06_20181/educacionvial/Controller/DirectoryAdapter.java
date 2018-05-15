package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Model.Dir;
import com.edu.udea.proyectointegrador.gr06_20181.educacionvial.R;

import java.util.List;

/**
 * Created by luisernesto on 3/05/18.
 */

public class DirectoryAdapter
        extends RecyclerView.Adapter<DirectoryAdapter.DirectoryViewHolder> {

    private List<Dir> items;
    private DirectoryAdapter.OnItemClickListener escucha;


    public void swapCursor(List<Dir> listDirections) {
        items = listDirections;
        notifyDataSetChanged();

    }

    public DirectoryAdapter(List<Dir> listDirections, OnItemClickListener listener) {
        items = listDirections;
        escucha = listener;
    }

    public interface OnItemClickListener {
        void onClick(DirectoryViewHolder holder, String numberDirectory);
    }


    @Override
    public DirectoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_directory, parent, false);
        DirectoryAdapter.DirectoryViewHolder tvh = new DirectoryAdapter.DirectoryViewHolder(view);
        return tvh;
    }

    @Override
    public void onBindViewHolder(DirectoryAdapter.DirectoryViewHolder holder, int position) {

        Dir dir = items.get(position);
        String s = dir.getTitle();
        holder.directionTitle.setText(s);
        s = dir.getDescription();
        holder.directionDescription.setText(s);
        s = dir.getNumber();

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class DirectoryViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        public TextView directionTitle, directionDescription;
        public ImageButton directionAction;
        public String directionNumber;

        public DirectoryViewHolder(View itemView) {
            super(itemView);

            directionTitle = (TextView) itemView.findViewById(R.id.title_dir);
            directionAction = (ImageButton) itemView.findViewById(R.id.action_dir);
            directionDescription = (TextView) itemView.findViewById(R.id.description_dir);

            directionAction.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            escucha.onClick(this, items.get(getAdapterPosition()).getNumber());
        }
    }

}
