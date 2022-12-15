package com.example.natour2122fe.adapter;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.natour2122fe.R;

public class SignHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView user, description, title;
    SignHolder.OnItemListener onItemListener;

    public SignHolder(@NonNull View itemView, SignHolder.OnItemListener onItemListener) {
        super(itemView);
        user = itemView.findViewById(R.id.signListItem_Username);
        description = itemView.findViewById(R.id.signListItem_Description);
        title = itemView.findViewById(R.id.signListItem_Title);
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
