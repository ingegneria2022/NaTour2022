package com.example.natour2122fe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.natour2122fe.Model.Feedback;
import com.example.natour2122fe.Model.Pathway;
import com.example.natour2122fe.Retrofit.PathwayApi;
import com.example.natour2122fe.Retrofit.RetrofitService;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsFeedbackActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spFeedbackStars;
    EditText etFeedbackDescription;
    private String feedbackStars;
    Button btnInsFeedback;
    boolean onItemSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ins_feedback);

        onItemSelect=false;
        int idPathway= getIntent().getIntExtra("idPathway", 0);
        String username= getIntent().getStringExtra("username");

        etFeedbackDescription=findViewById(R.id.etFeedbackDescription);
        spFeedbackStars = findViewById(R.id.spFeedbackStars);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.FeedbackVote, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFeedbackStars.setAdapter(adapter);
        spFeedbackStars.setOnItemSelectedListener(this);

        btnInsFeedback=findViewById(R.id.btnInsFeedback);
        btnInsFeedback.setOnClickListener(v->{
            String feedbackDescription = String.valueOf(etFeedbackDescription.getText());
            Feedback feedback = new Feedback();
            feedback.setDescription(feedbackDescription);
            if(onItemSelect) {
                feedback.setVote(feedbackStars);
                feedback.setUsername(username);
                feedback.setIdPathway(idPathway);
                saveFeedback(feedback);
            }else{
                Toast.makeText(this, "Non hai selezionato il voto dallo spinner", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveFeedback(Feedback newFeedback) {
        //retrofit
        RetrofitService retrofitService = new RetrofitService();
        PathwayApi pathwayApi = retrofitService.getRetrofit().create(PathwayApi.class);
        pathwayApi.addFeedback(newFeedback)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(InsFeedbackActivity.this,"Inserimento del percorso avvenuto con successo", Toast.LENGTH_SHORT).show();
                        showFeedbackActivity(newFeedback.getUsername(),newFeedback.getIdPathway());
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(InsFeedbackActivity.this,"Inserimento del percorso non avvenuto", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(InsPathwayActivity.class.getName()).log(Level.SEVERE,"Errore che occorre:",t);

                    }
                });
    }

    private void showFeedbackActivity(String user, int id) {
        Intent openPage = new Intent(this, FeedbackActivity.class);
        openPage.putExtra("username",user);
        openPage.putExtra("idPathway",id);
        startActivity(openPage);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            onItemSelect= true;
            feedbackStars = String.valueOf(adapterView.getItemAtPosition(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }
}