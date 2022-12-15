package com.example.natour2122fe.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.natour2122fe.R;

public class PhotoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView photoView;
    PhotoHolder.OnItemListener onItemListener;

    public PhotoHolder(@NonNull View itemView, PhotoHolder.OnItemListener onItemListener) {
        super(itemView);
        photoView = itemView.findViewById(R.id.photoView);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Log.d("position", "onClick: " + getAdapterPosition());
        onItemListener.onItemClick(getAdapterPosition());
    }

    public interface OnItemListener{
        void onItemClick(int position);
    }
}
