package com.example.natour2122fe;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.natour2122fe.Model.InterestPoints;
import com.example.natour2122fe.Model.Pathway;
import com.example.natour2122fe.Retrofit.PathwayApi;
import com.example.natour2122fe.Retrofit.RetrofitService;
import com.example.natour2122fe.chat.ListChatActivity;
import com.example.natour2122fe.login.AmplifyCognito;
import com.example.natour2122fe.login.google.GoogleLoginActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InterestPointsActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    Button btnInsPoint;
    String username;
    Integer idPathway;
    CameraPosition positionPoint;
    Pathway pathway;
    TextView tvPathwayName;
    //toolbar
    AmplifyCognito amplifyCognito;
    ImageView ivUserTB, btnSignal , btnChat,btnHomeTB;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    //fine toolbar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_points);
        pathway = (Pathway) getIntent().getSerializableExtra("Pathway");
        idPathway= pathway.getId();
        username = getIntent().getStringExtra("username");
        toolbar(username);

        tvPathwayName=findViewById(R.id.tvPathwayName);
        tvPathwayName.setText(pathway.getName());
        btnInsPoint=findViewById(R.id.btnInsPoint);
        btnInsPoint.setOnClickListener(v->{
            Intent openPage = new Intent(this, InsInterestPointsActivity.class);
            openPage.putExtra("Pathway",pathway);
            openPage.putExtra("username", username);
            startActivity(openPage);
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.mapInterestPoints);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady( GoogleMap googleMap) {
        loadPoint(idPathway,googleMap);
    }

    private void loadPoint(Integer idPathway, GoogleMap googleMap) {
        RetrofitService retrofitService = new RetrofitService();
        PathwayApi pathwayApi = retrofitService.getRetrofit().create(PathwayApi.class);
        pathwayApi.viewInterestPoints(idPathway)
                .enqueue(new Callback<List<InterestPoints>>() {
                    @Override
                    public void onResponse(Call<List<InterestPoints>> call, Response<List<InterestPoints>> response) {
                        populateMapView(response.body(),googleMap);
                    }
                    @Override
                    public void onFailure(Call<List<InterestPoints>> call, Throwable t) {
                        Toast.makeText(InterestPointsActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void populateMapView(List<InterestPoints> interestPoints, GoogleMap googleMap) {
        Integer idPathway = getIntent().getIntExtra("idPathway",0);
        map= googleMap;
        if(interestPoints.size()!= 0) {
            positionPoint = new CameraPosition.Builder().target(new LatLng(interestPoints.get(0).getLatitude(), interestPoints.get(0).getLongitude())).zoom(13).build();
            for (int i = 0; i <= interestPoints.size() - 1; i++) {
                Marker startMarker = map.addMarker(new MarkerOptions().position(new LatLng(interestPoints.get(i).getLatitude(), interestPoints.get(i).getLongitude())).title("interestPoint" + interestPoints.get(i).getName() + "").draggable(false));
            }

            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(positionPoint));
        }else{
            Toast.makeText(this, "Non esistono, ancora, punti di interesse per questo percorso", Toast.LENGTH_SHORT).show();
        }
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
            Intent intent2 = new Intent(InterestPointsActivity.this, HomeActivity.class);
            intent2.putExtra("username", username);
            startActivity(intent2);
        });
        btnSignal=findViewById(R.id.btnSignal);
        btnSignal.setOnClickListener(v->{
            Intent intent4 = new Intent(InterestPointsActivity.this, SignalingActivity.class);
            intent4.putExtra("username",username);
            startActivity(intent4);
        });
        ivUserTB = findViewById(R.id.btnUser);
        ivUserTB.setOnClickListener(v ->
        {
            if(googleAccountIsIn ()){
                //google
                Toast.makeText(this, "Google Account: "+username+ "", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(InterestPointsActivity.this, GoogleLoginActivity.class);
                startActivity(intent2);
            }else{
                //amazon
                loadPopUp();
                amplifyCognito = new AmplifyCognito(getApplicationContext());
                amplifyCognito.userAttributes(username);}
        });
        btnChat=findViewById(R.id.btnChat);
        btnChat.setOnClickListener(v->{
            Intent intent1 = new Intent(InterestPointsActivity.this, ListChatActivity.class );
            startActivity(intent1);
        });
    }

}