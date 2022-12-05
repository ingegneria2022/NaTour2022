package com.example.natour2122fe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.natour2122fe.Model.Pathway;
import com.example.natour2122fe.Model.PathwaySignaling;
import com.example.natour2122fe.Model.RespondSignaling;
import com.example.natour2122fe.Retrofit.PathwayApi;
import com.example.natour2122fe.Retrofit.RetrofitService;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsResponsPathwaySignalitedActivity extends AppCompatActivity {

    PathwaySignaling pathwaySign;
    EditText etRespond;
    TextView tvSignTitle,tvSignDescr;
    Button btnSendRespond;
    String admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ins_respons_pathway_signalited);

        pathwaySign = (PathwaySignaling) getIntent().getSerializableExtra("pathwaySign");
        Intent intent = getIntent();
        admin = intent.getStringExtra("admin");
        findView();
        tvSignDescr.setText(pathwaySign.getDescriptionSign());
        tvSignTitle.setText(pathwaySign.getTitle());
        btnSendRespond.setOnClickListener(v->{
            if (etRespond.getText().toString().length() == 0) {
                etRespond.setError(getResources().getString(R.string.mandatory));
            } else {
                RespondSignaling respondSignaling=new RespondSignaling();
                respondSignaling.setDescriptionRisp(etRespond.getText().toString());
                respondSignaling.setUsername(String.valueOf(pathwaySign.getUsername()));
                respondSignaling.setAdmin(String.valueOf(admin));
                saveRespond(respondSignaling);
            }
        });
    }

    private void findView() {
        tvSignTitle=findViewById(R.id.tvSignTitle);
        tvSignDescr=findViewById(R.id.tvSignDescr);
        etRespond=findViewById(R.id.etRespond);
        btnSendRespond=findViewById(R.id.btnSendRespond);
    }

    private void saveRespond(RespondSignaling respondSignaling) {
        RetrofitService retrofitService = new RetrofitService();
        PathwayApi pathwayApi = retrofitService.getRetrofit().create(PathwayApi.class);
        pathwayApi.addRespondSignaling(respondSignaling)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(InsResponsPathwaySignalitedActivity.this,"Inserimento della risposta avvenuto con successo", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(InsResponsPathwaySignalitedActivity.this,"Inserimento della risposta non avvenuto", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(InsPathwayActivity.class.getName()).log(Level.SEVERE,"Errore che occorre:",t);
                    }
                });
    }
}