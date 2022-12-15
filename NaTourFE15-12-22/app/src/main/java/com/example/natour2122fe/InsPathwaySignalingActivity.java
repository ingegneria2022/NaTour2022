package com.example.natour2122fe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.natour2122fe.Model.Pathway;
import com.example.natour2122fe.Model.PathwaySignaling;
import com.example.natour2122fe.Retrofit.PathwayApi;
import com.example.natour2122fe.Retrofit.RetrofitService;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsPathwaySignalingActivity extends AppCompatActivity {

    EditText etPathwaySignalingTitle,etPathwaySignalingDescription;
    Button btnInsPathwaySignaling;
    String signalingTitle,signalingDescription,username;
    Integer idPathway;
    Pathway pathway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ins_pathway_signaling);

        pathway = (Pathway) getIntent().getSerializableExtra("Pathway");
        idPathway= pathway.getId();
        username = getIntent().getStringExtra("username");
        findView();

        btnInsPathwaySignaling.setOnClickListener(v->{
            signalingTitle=String.valueOf(etPathwaySignalingDescription.getText());
            signalingDescription= String.valueOf(etPathwaySignalingTitle.getText());
            PathwaySignaling pathwaySignaling= new PathwaySignaling();
            pathwaySignaling.setTitle(signalingTitle);
            pathwaySignaling.setDescriptionSign(signalingDescription);
            pathwaySignaling.setUsername(username);
            pathwaySignaling.setIdPathway(idPathway);

            sendSign(pathwaySignaling);
        });
    }

    private void findView() {
        etPathwaySignalingDescription=findViewById(R.id.etPathwaySignalingDescription);
        etPathwaySignalingTitle=findViewById(R.id.etPathwaySignalingTitle);
        btnInsPathwaySignaling=findViewById(R.id.btnInsPathwaySignaling);
    }

    private void sendSign(PathwaySignaling newPathwaySignaling) {
        RetrofitService retrofitService = new RetrofitService();
        PathwayApi pathwayApi = retrofitService.getRetrofit().create(PathwayApi.class);
        pathwayApi.addPathwaySignaling(newPathwaySignaling)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(InsPathwaySignalingActivity.this,"Inserimento della segnalazione, avvenuto con successo", Toast.LENGTH_SHORT).show();
                        showRute(username, pathway);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(InsPathwaySignalingActivity.this,"Inserimento della segnalazione, non avvenuto", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(InsInterestPointsActivity.class.getName()).log(Level.SEVERE,"Errore che occorre:",t);
                    }
                });
    }

    private void showRute(String username, Pathway pathway) {
        Intent intent = new Intent(InsPathwaySignalingActivity.this, RuteDetailsActivityScr.class);
        intent.putExtra("username",username);
        intent.putExtra("Pathway",pathway);
        startActivity(intent);
    }
}