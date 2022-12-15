package com.example.natour2122fe.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.natour2122fe.Model.User;
import com.example.natour2122fe.R;
import com.example.natour2122fe.Retrofit.PathwayApi;
import com.example.natour2122fe.Retrofit.RetrofitService;
import com.example.natour2122fe.VisPathwaySignalitedActivity;
import com.example.natour2122fe.login.AmplifyCognito;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminActivity extends AppCompatActivity {

    AmplifyCognito amplifyCognito;
    TextView tvAdmin;
    Button btnSnout, btnVisSign;
    ImageView ivPhotoUserAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        amplifyCognito = new AmplifyCognito(getApplicationContext());
        /*Write username*/
        tvAdmin=findViewById(R.id.tvAdmin);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String ciao = "Ciao Amministratore "+ username + "!";
        tvAdmin.setText(ciao);
        //visualizza foto Utente
        ivPhotoUserAdmin=findViewById(R.id.ivPhotoUserAdmin);
        viewUser(username);
        /*Visualizza Segnalazione Percorsi*/
        btnVisSign=findViewById(R.id.btnVisSign);
        btnVisSign.setOnClickListener(v->{
            Intent openPage = new Intent(this, VisPathwaySignalitedActivity.class);
            openPage.putExtra("username",username);
            startActivity(openPage);
        });
        /* button logout */
        btnSnout = findViewById(R.id.btnSignOutAdmin);
        btnSnout.setOnClickListener(v -> {
            loadPopUp();
            amplifyCognito.signOut();
        });

    }
    private void viewUser(String username) {
        RetrofitService retrofitService = new RetrofitService();
        PathwayApi pathwayApi = retrofitService.getRetrofit().create(PathwayApi.class);
        pathwayApi.viewUser(username)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        //Log.i( "onResponse: ", response.body().toString());
                        assert response.body() != null;
                        populateUserView(response.body());
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(AdminActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.i("onFailure:", t.getMessage());
                    }
                });
    }

    private void populateUserView(User user) {
        String link= "https://pathway-image.s3.amazonaws.com/"+ user.getPhoto();
        Glide.with(AdminActivity.this)
                .load(link)
                .circleCrop()
                .into(ivPhotoUserAdmin);
    }
    private void loadPopUp() {
        setContentView(R.layout.activity_pop_up_loading);
        /*settare la metrica cosi che sebri un pop up anche se è una activity*/
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.6));
    }
}