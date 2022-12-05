package com.example.natour2122fe.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.natour2122fe.Model.Pathway;
import com.example.natour2122fe.R;

import java.util.List;

public class PathwayAdapter extends RecyclerView.Adapter<PathwayHolder>  {

    private List<Pathway> pathwayList;
    private PathwayHolder.OnItemListener pathwayOnItemListener;

    public PathwayAdapter(List<Pathway> pathwayList, PathwayHolder.OnItemListener onItemListener) {
        this.pathwayList = pathwayList;
        this.pathwayOnItemListener = onItemListener;
    }

    public void setFilteredList(List<Pathway> filteredList){
        this.pathwayList=filteredList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public PathwayHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_pathway, parent, false);
        return new PathwayHolder(view, pathwayOnItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PathwayHolder holder, int position) {
        Pathway pathway = pathwayList.get(position);
        holder.name.setText(pathway.getName());
        holder.duration.setText(pathway.getDuration());
        holder.city.setText(pathway.getCity());
    }

    @Override
    public int getItemCount() {

        return pathwayList.size();
    }


}
