package com.example.natour2122fe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.natour2122fe.Model.Pathway;
import com.example.natour2122fe.Model.PathwaySignaling;
import com.example.natour2122fe.Retrofit.PathwayApi;
import com.example.natour2122fe.Retrofit.RetrofitService;
import com.example.natour2122fe.adapter.PathwayAdapter;
import com.example.natour2122fe.adapter.PathwayHolder;
import com.example.natour2122fe.adapter.SignAdapter;
import com.example.natour2122fe.adapter.SignHolder;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisPathwaySignalitedActivity extends AppCompatActivity implements SignHolder.OnItemListener {
    private RecyclerView recyclerView;
    private SignAdapter signAdapter;
    List<PathwaySignaling> pathwaySignList1;
    private String admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vis_pathway_signalited);

        Intent intent = getIntent();
        admin = intent.getStringExtra("username");

        recyclerView = findViewById(R.id.signList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadPathway() {
        RetrofitService retrofitService = new RetrofitService();
        PathwayApi pathwayApi = retrofitService.getRetrofit().create(PathwayApi.class);
        pathwayApi.viewPathwaySign()
                .enqueue(new Callback<List<PathwaySignaling>>() {
                    @Override
                    public void onResponse(Call<List<PathwaySignaling>> call, Response<List<PathwaySignaling>> response) {
                        populateListView(response.body());
                    }
                    @Override
                    public void onFailure(Call<List<PathwaySignaling>> call, Throwable t) {
                        Toast.makeText(VisPathwaySignalitedActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.i("onFailure:", t.getMessage());
                    }
                });
    }

    private void populateListView(List<PathwaySignaling> pathwaySignList) {
        pathwaySignList1= pathwaySignList;
        signAdapter = new SignAdapter(pathwaySignList, this);
        recyclerView.setAdapter(signAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPathway();
    }

    @Override
    public void onItemClick(int position) {
        PathwaySignaling pathwaySign = pathwaySignList1.get(position);
        Intent intent = new Intent(this, InsResponsPathwaySignalitedActivity.class);
        intent.putExtra("admin",admin);
        intent.putExtra("pathwaySign", pathwaySign);
        startActivity(intent);
    }
}