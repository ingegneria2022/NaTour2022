package com.example.natour2122fe.adapter;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.natour2122fe.R;

public class PathwayHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView name, duration, city;
    OnItemListener onItemListener;

    public PathwayHolder(@NonNull View itemView, OnItemListener onItemListener) {
        super(itemView);
        name = itemView.findViewById(R.id.pathwayListItem_name);
        duration = itemView.findViewById(R.id.pathwayListItem_Duration);
        city = itemView.findViewById(R.id.pathwayListItem_City);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.d("position", "onClick: " + getAdapterPosition());
        onItemListener.onItemClick(getAdapterPosition());
    }

    public interface OnItemListener{
        void onItemClick(int position);
    }
}