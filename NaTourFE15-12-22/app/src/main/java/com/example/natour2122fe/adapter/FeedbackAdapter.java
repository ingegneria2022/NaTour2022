package com.example.natour2122fe.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.natour2122fe.FeedbackActivity;
import com.example.natour2122fe.Model.Feedback;
import com.example.natour2122fe.R;

import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackHolder>  {


    private List<Feedback> feedbackList;
    private FeedbackHolder.OnItemListener feedbackOnItemListener;

    public FeedbackAdapter(List<Feedback> feedbackList, FeedbackActivity feedbackActivity) {
        this.feedbackList = feedbackList;
    }


    @NonNull
    @Override
    public FeedbackHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feedback_list, parent, false);
        return new FeedbackHolder(view, feedbackOnItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackHolder holder, int position) {
        Feedback feedback = feedbackList.get(position);
        holder.vote.setText(feedback.getVote().toString());
        holder.description.setText(feedback.getDescription());
        holder.username.setText(feedback.getUsername());
    }

    @Override
    public int getItemCount() {

        return feedbackList.size();
    }


}
