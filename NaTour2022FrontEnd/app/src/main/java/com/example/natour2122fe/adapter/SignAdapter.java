package com.example.natour2122fe.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.natour2122fe.Model.PathwaySignaling;
import com.example.natour2122fe.R;

import java.util.List;

public class SignAdapter extends RecyclerView.Adapter<SignHolder> {

    private final SignHolder.OnItemListener signOnItemListener;
    private List<PathwaySignaling> signList;

    public SignAdapter(List<PathwaySignaling> signList, SignHolder.OnItemListener onItemListener) {
        this.signList = signList;
        this.signOnItemListener = onItemListener;
    }

    public void setFilteredList(List<PathwaySignaling> filteredList){
        this.signList=filteredList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public SignHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sign_list, parent, false);
        return new SignHolder(view, signOnItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SignHolder holder, int position) {
        PathwaySignaling pathwaySign = signList.get(position);
        holder.user.setText(pathwaySign.getUsername());
        holder.description.setText(pathwaySign.getDescriptionSign());
        holder.title.setText(pathwaySign.getTitle());
    }

    @Override
    public int getItemCount() {

        return signList.size();
    }


}