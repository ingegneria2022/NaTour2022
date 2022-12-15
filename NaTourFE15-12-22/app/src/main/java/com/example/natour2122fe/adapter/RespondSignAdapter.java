package com.example.natour2122fe.adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.natour2122fe.Model.RespondSignaling;
import com.example.natour2122fe.R;
import com.example.natour2122fe.SignalingActivity;
import java.util.List;

public class RespondSignAdapter extends RecyclerView.Adapter<RespondSignHolder>  {


    private List<RespondSignaling> respondSignList;
    private RespondSignHolder.OnItemListener respondSignOnItemListener;

    public RespondSignAdapter(List<RespondSignaling> respondSignList, SignalingActivity respondSignActivity) {
        this.respondSignList = respondSignList;
    }


    @NonNull
    @Override
    public RespondSignHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.signaling_list, parent, false);
        return new RespondSignHolder(view, respondSignOnItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RespondSignHolder holder, int position) {
        RespondSignaling respondSignaling = respondSignList.get(position);
        Log.i("onBindViewHolder: ", String.valueOf(respondSignaling.getDescriptionRisp()));
        holder.description.setText(respondSignaling.getDescriptionRisp());
        holder.admin.setText(respondSignaling.getAdmin());
    }

    @Override
    public int getItemCount() {
        return respondSignList.size();
    }


}