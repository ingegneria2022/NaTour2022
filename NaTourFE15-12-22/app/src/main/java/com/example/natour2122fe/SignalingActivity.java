package com.example.natour2122fe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.natour2122fe.Model.Feedback;
import com.example.natour2122fe.Model.RespondSignaling;
import com.example.natour2122fe.Retrofit.PathwayApi;
import com.example.natour2122fe.Retrofit.RetrofitService;
import com.example.natour2122fe.adapter.FeedbackAdapter;
import com.example.natour2122fe.adapter.RespondSignAdapter;
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

public class SignalingActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<RespondSignaling> respondSignalings1;
    RespondSignAdapter respondSignAdapter;
    String username;
    //toolbar
    AmplifyCognito amplifyCognito;
    ImageView ivUserTB, btnSignal , btnChat, btnHomeTB;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signaling);

        recyclerView = findViewById(R.id.RespondSignalingList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        username=intent.getStringExtra("username");
        toolbar(username);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadSign();
    }

    private void loadSign() {
        RetrofitService retrofitService = new RetrofitService();
        PathwayApi pathwayApi = retrofitService.getRetrofit().create(PathwayApi.class);
        pathwayApi.respondSignaling(username)
                .enqueue(new Callback<List<RespondSignaling>>() {
                    @Override
                    public void onResponse(Call<List<RespondSignaling>> call, Response<List<RespondSignaling>> response) {
                        populateListView(response.body());
                    }
                    @Override
                    public void onFailure(Call<List<RespondSignaling>> call, Throwable t) {
                        Toast.makeText(SignalingActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void populateListView(List<RespondSignaling> respondSignalings) {
        respondSignalings1= respondSignalings;
        respondSignAdapter = new RespondSignAdapter(respondSignalings,this);
        recyclerView.setAdapter(respondSignAdapter);
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
    private void loadPopUp() {
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
            Intent intent2 = new Intent(SignalingActivity.this, HomeActivity.class);
            intent2.putExtra("username", username);
            startActivity(intent2);
        });
        btnSignal=findViewById(R.id.btnSignal);
        btnSignal.setOnClickListener(v->{
            Intent intent4 = new Intent(SignalingActivity.this, SignalingActivity.class);
            intent4.putExtra("username",username);
            startActivity(intent4);
        });
        ivUserTB = findViewById(R.id.btnUser);
        ivUserTB.setOnClickListener(v ->
        {
            if(googleAccountIsIn ()){
                //google
                Toast.makeText(this, "Google Account: "+username+ "", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(SignalingActivity.this, GoogleLoginActivity.class);
                startActivity(intent2);
            }else{
                //amazon
                loadPopUp();
                amplifyCognito = new AmplifyCognito(getApplicationContext());
                amplifyCognito.userAttributes(username);}
        });
        btnChat=findViewById(R.id.btnChat);
        btnChat.setOnClickListener(v->{
            Intent intent1 = new Intent(SignalingActivity.this, ListChatActivity.class );
            startActivity(intent1);
        });
    }
}