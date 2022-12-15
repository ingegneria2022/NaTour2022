package com.example.natour2122fe.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.natour2122fe.R;

public class FeedbackHolder extends RecyclerView.ViewHolder{

    TextView vote, description, username;

    public FeedbackHolder(@NonNull View itemView, FeedbackHolder.OnItemListener feedbackOnItemListener) {
        super(itemView);
        vote = itemView.findViewById(R.id.feedbackListItem_Vote);
        description = itemView.findViewById(R.id.feedbackListItem_Description);
        username = itemView.findViewById(R.id.feedbackListItem_Username);
    }

    public class OnItemListener {
    }
}
