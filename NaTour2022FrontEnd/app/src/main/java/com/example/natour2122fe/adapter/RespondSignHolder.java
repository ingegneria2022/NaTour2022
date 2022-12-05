package com.example.natour2122fe.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.natour2122fe.R;

public class RespondSignHolder extends RecyclerView.ViewHolder{

    TextView admin, description;

    public RespondSignHolder(@NonNull View itemView, RespondSignHolder.OnItemListener respondSignOnItemListener) {
        super(itemView);

        description = itemView.findViewById(R.id.signalingListItem_Description);
        admin = itemView.findViewById(R.id.signalingListItem_Admin);
    }

    public class OnItemListener {
    }
}

