package com.example.natour2122fe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.natour2122fe.Model.Pathway;
import com.example.natour2122fe.Model.Photo;
import com.example.natour2122fe.Retrofit.PathwayApi;
import com.example.natour2122fe.Retrofit.RetrofitService;
import com.example.natour2122fe.adapter.PathwayAdapter;
import com.example.natour2122fe.adapter.PathwayHolder;
import com.example.natour2122fe.adapter.PhotoAdapter;
import com.example.natour2122fe.adapter.PhotoHolder;
import com.example.natour2122fe.chat.ListChatActivity;
import com.example.natour2122fe.login.AmplifyCognito;
import com.example.natour2122fe.login.google.GoogleLoginActivity;
import com.example.natour2122fe.login.popUpErrorActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoActivity extends AppCompatActivity implements PhotoHolder.OnItemListener, AdapterView.OnItemSelectedListener{

    String username;
    //toolbar
    AmplifyCognito amplifyCognito;
    ImageView ivUserTB, btnSignal , btnChat,btnHomeTB;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    //fine toolbar
    int idPathway;
    private RecyclerView recyclerView;
    PhotoAdapter photoAdapter;
    private List<Photo> photoList1;
    TextView tvInsPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        idPathway = getIntent().getIntExtra("idPathway", 0);
        username = getIntent().getStringExtra("username");
        Log.i("onCreate: ", username+idPathway);
        toolbar(username);

        tvInsPhoto=findViewById(R.id.tvInsPhoto2);
        tvInsPhoto.setOnClickListener(v->{
            Intent openPage = new Intent(this, InsPhotoActivity.class);
            openPage.putExtra("idPathway",idPathway);
            openPage.putExtra("username", username);
            startActivity(openPage);
        });

        recyclerView = findViewById(R.id.photoList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }



    private void loadPhoto(Integer idPathway) {
        RetrofitService retrofitService = new RetrofitService();
        PathwayApi pathwayApi = retrofitService.getRetrofit().create(PathwayApi.class);
        pathwayApi.viewPhoto(idPathway)
                .enqueue(new Callback<List<Photo>>() {
                    @Override
                    public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                        Log.i( "onResponse: ", response.body().toString());
                        populateListView(response.body());
                    }
                    @Override
                    public void onFailure(Call<List<Photo>> call, Throwable t) {
                        Toast.makeText(PhotoActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.i("onFailure:", t.getMessage());
                    }
                });
    }

    private void populateListView(List<Photo> photoList) {
        photoList1= photoList;
        photoAdapter = new PhotoAdapter(photoList,this);
        recyclerView.setAdapter(photoAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPhoto(idPathway);
    }


    @Override
    public void onItemClick(int position) {
        Photo photo = photoList1.get(position);
        Intent intent = new Intent(this, popUpPhotoActivity.class);
        intent.putExtra("namePhoto", photo.getName());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
        //Intent intent = new Intent(this, VisPhotoActivity.class);
        //intent.putExtra("username",username);
        //intent.putExtra("pathwayName", pathway);
        //startActivity(intent);
    }




    private void loadPopUpLoading() {
        setContentView(R.layout.activity_pop_up_loading);
        /*settare la metrica cosi che sebri un pop up anche se è una activity*/
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.6));
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

    public void toolbar(String username){
        btnHomeTB = findViewById(R.id.btnHome);
        btnHomeTB.setOnClickListener(v ->
        {
            Intent intent2 = new Intent(PhotoActivity.this, HomeActivity.class);
            intent2.putExtra("username", username);
            startActivity(intent2);
        });
        btnSignal=findViewById(R.id.btnSignal);
        btnSignal.setOnClickListener(v->{
            Intent intent4 = new Intent(PhotoActivity.this, SignalingActivity.class);
            intent4.putExtra("username",username);
            startActivity(intent4);
        });
        ivUserTB = findViewById(R.id.btnUser);
        ivUserTB.setOnClickListener(v ->
        {
            if(googleAccountIsIn ()){
                //google
                Toast.makeText(this, "Google Account: "+username+ "", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(PhotoActivity.this, GoogleLoginActivity.class);
                startActivity(intent2);
            }else{
                //amazon
                loadPopUpLoading();
                amplifyCognito = new AmplifyCognito(getApplicationContext());
                amplifyCognito.userAttributes(username);}
        });
        btnChat=findViewById(R.id.btnChat);
        btnChat.setOnClickListener(v->{
            Intent intent1 = new Intent(PhotoActivity.this, ListChatActivity.class );
            startActivity(intent1);
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}