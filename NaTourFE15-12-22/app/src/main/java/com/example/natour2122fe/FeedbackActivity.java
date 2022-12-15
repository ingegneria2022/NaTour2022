package com.example.natour2122fe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.natour2122fe.Model.Feedback;
import com.example.natour2122fe.Model.Pathway;
import com.example.natour2122fe.Retrofit.PathwayApi;
import com.example.natour2122fe.Retrofit.RetrofitService;
import com.example.natour2122fe.adapter.FeedbackAdapter;
import com.example.natour2122fe.adapter.PathwayAdapter;
import com.example.natour2122fe.chat.ListChatActivity;
import com.example.natour2122fe.login.AmplifyCognito;
import com.example.natour2122fe.login.google.GoogleLoginActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackActivity extends AppCompatActivity {
    private List<Feedback> feedbackList1;
    private RecyclerView recyclerView;
    FeedbackAdapter feedbackAdapter;
    Button btnInsFeedback;
    TextView tvPathwayName;
    Pathway pathway;
    //toolbar
    AmplifyCognito amplifyCognito;
    ImageView ivUserTB, btnSignal , btnChat,btnHomeTB;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    //fine toolbar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);


        pathway = (Pathway) getIntent().getSerializableExtra("Pathway");
        int idPathway = getIntent().getIntExtra("idPathway", 0);
        String username = getIntent().getStringExtra("username");
        toolbar(username);
        tvPathwayName=findViewById(R.id.tvPathwayName);
        tvPathwayName.setText(pathway.getName());
        btnInsFeedback=findViewById(R.id.btnInsFeedback);
        int finalIdPathway = idPathway;
        btnInsFeedback.setOnClickListener(v->{
            Intent openPage = new Intent(this, InsFeedbackActivity.class);
            openPage.putExtra("idPathway", finalIdPathway);
            openPage.putExtra("username", username);
            startActivity(openPage);
        });
        recyclerView = findViewById(R.id.feedbackList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadFeedback(idPathway);
    }

    private void loadFeedback(Integer idPathway) {
        RetrofitService retrofitService = new RetrofitService();
        PathwayApi pathwayApi = retrofitService.getRetrofit().create(PathwayApi.class);
        pathwayApi.viewFeedback(idPathway)
                .enqueue(new Callback<List<Feedback>>() {
                    @Override
                    public void onResponse(Call<List<Feedback>> call, Response<List<Feedback>> response) {
                        populateListView(response.body());
                    }
                    @Override
                    public void onFailure(Call<List<Feedback>> call, Throwable t) {
                        Toast.makeText(FeedbackActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.i("onFailure:", t.getMessage());
                    }
                });
    }

    private void populateListView(List<Feedback> feedbackList) {
        feedbackList1= feedbackList;
        feedbackAdapter = new FeedbackAdapter(feedbackList,this);
        recyclerView.setAdapter(feedbackAdapter);
    }

    public boolean googleAccountIsIn (){
        boolean isIn = false;
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            isIn=true;
        }
        return isIn;
    }
    private void loadPopUpLoading() {
        setContentView(R.layout.activity_pop_up_loading);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.6));
    }
    public void toolbar(String username){
        btnHomeTB = findViewById(R.id.btnHome);
        btnHomeTB.setOnClickListener(v ->
        {
            Intent intent2 = new Intent(FeedbackActivity.this, HomeActivity.class);
            intent2.putExtra("username", username);
            startActivity(intent2);
        });
        btnSignal=findViewById(R.id.btnSignal);
        btnSignal.setOnClickListener(v->{
            Intent intent4 = new Intent(FeedbackActivity.this, SignalingActivity.class);
            intent4.putExtra("username",username);
            startActivity(intent4);
        });
        ivUserTB = findViewById(R.id.btnUser);
        ivUserTB.setOnClickListener(v ->
        {
            if(googleAccountIsIn ()){
                //google
                Toast.makeText(this, "Google Account: "+username+ "", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(FeedbackActivity.this, GoogleLoginActivity.class);
                startActivity(intent2);
            }else{
                //amazon
                loadPopUpLoading();
                amplifyCognito = new AmplifyCognito(getApplicationContext());
                amplifyCognito.userAttributes(username);}
        });
        btnChat=findViewById(R.id.btnChat);
        btnChat.setOnClickListener(v->{
            Intent intent1 = new Intent(FeedbackActivity.this, ListChatActivity.class );
            startActivity(intent1);
        });
    }
}