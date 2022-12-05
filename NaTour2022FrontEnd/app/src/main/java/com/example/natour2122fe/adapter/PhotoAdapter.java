package com.example.natour2122fe.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.natour2122fe.Model.Photo;
import com.example.natour2122fe.PhotoActivity;
import com.example.natour2122fe.R;
import com.example.natour2122fe.login.google.GoogleLoginActivity;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoHolder>  {

    private List<Photo> photoList;
    private PhotoHolder.OnItemListener photoOnItemListener;

    public PhotoAdapter(List<Photo> photoList, PhotoHolder.OnItemListener onItemListener) {
        this.photoList = photoList;
        this.photoOnItemListener = onItemListener;
    }

    @NonNull
    @Override
    public PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_photo, parent, false);
        return new PhotoHolder(view, photoOnItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoHolder holder, int position) {
        Photo photo = photoList.get(position);
        String link= "https://pathway-image.s3.amazonaws.com/"+ photo.getName();
        Glide.with(holder.photoView.getContext())
                .load(link)
                .into(holder.photoView);
        //holder.photoView.setImageURI(Uri.parse(link));
    }

    @Override
    public int getItemCount() {

        return photoList.size();
    }


}

