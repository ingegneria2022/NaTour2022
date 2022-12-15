package com.example.natour2122fe;

import android.content.Intent;
import android.os.Bundle;

import com.example.natour2122fe.Model.InterestPoints;
import com.example.natour2122fe.Model.Pathway;
import com.example.natour2122fe.Retrofit.PathwayApi;
import com.example.natour2122fe.Retrofit.RetrofitService;
import com.example.natour2122fe.chat.ListChatActivity;
import com.example.natour2122fe.databinding.ActivityRuteDetailsScrBinding;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RuteDetailsActivityScr extends AppCompatActivity implements OnMapReadyCallback {

    private ActivityRuteDetailsScrBinding binding;
    Pathway pathway= new Pathway();
    TextView etPathwayName, etPathwayDescription, etPathwayDuration, etPathwayDifficulty, tvPathwaySignaling;
    Button btnFeedback, btnInsInterestingPoints, btnReportPathway, btnPhotoPathway;
    GoogleMap map;
    String username;
    CameraPosition positionPoint;
    //toolbar
    AmplifyCognito amplifyCognito;
    ImageView ivUserTB, btnSignal , btnChat,btnHomeTB;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    //fine toolbar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRuteDetailsScrBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        findView();
        username= getIntent().getStringExtra("username");
        toolbar(username);
        pathway = (Pathway) getIntent().getSerializableExtra("pathwayName");
        Integer idPathway=pathway.getId();
       // if(pathway.getId()!= null){
            pathwayIsSignaling(idPathway);
       // }


        btnFeedback.setOnClickListener(v->{
            Intent openPage = new Intent(this, FeedbackActivity.class);
            openPage.putExtra("idPathway",pathway.getId());
            openPage.putExtra("Pathway",pathway);
            openPage.putExtra("username", username);
            startActivity(openPage);
        });

        btnInsInterestingPoints.setOnClickListener(v->{
            Intent openPage2 = new Intent(this, InterestPointsActivity.class);
            openPage2.putExtra("Pathway",pathway);
            openPage2.putExtra("username", username);
            startActivity(openPage2);
        });

        btnReportPathway.setOnClickListener(v->{
            Intent openPage3 = new Intent(this, InsPathwaySignalingActivity.class);
            openPage3.putExtra("Pathway",pathway);
            openPage3.putExtra("username", username);
            startActivity(openPage3);
        });

        btnPhotoPathway.setOnClickListener(v->{
            Intent openPage4 = new Intent(this, PhotoActivity.class);
            openPage4.putExtra("idPathway",pathway.getId());
            openPage4.putExtra("Pathway",pathway);
            openPage4.putExtra("username", username);
            startActivity(openPage4);
        });

        etPathwayName.setText(pathway.getName());
        etPathwayDescription.setText(pathway.getDescription());
        etPathwayDuration.setText(pathway.getDuration());
        etPathwayDifficulty.setText(pathway.getDifficulty());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void findView() {
        btnInsInterestingPoints = findViewById(R.id.btnInsInterestingPoints);
        btnFeedback = findViewById(R.id.btnFeedback);
        btnReportPathway=findViewById(R.id.btnReportPathway);
        btnPhotoPathway=findViewById(R.id.btnPhotoPathway);
        etPathwayName = findViewById(R.id.etPathwayName);
        etPathwayDescription = findViewById(R.id.etPathwayDescription);
        etPathwayDuration = findViewById(R.id.etPathwayDuration);
        etPathwayDifficulty = findViewById(R.id.etPathwayDifficulty);
    }

    private void pathwayIsSignaling(Integer idPathway) {
        RetrofitService retrofitService = new RetrofitService();
        PathwayApi pathwayApi = retrofitService.getRetrofit().create(PathwayApi.class);
        pathwayApi.contPathwaySignaling(idPathway)
                .enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if(response.body()!=null){
                            pathwaySignaleted(response.body().intValue());
                        }
                    }
                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                    }
                });
    }

    private void pathwaySignaleted(int value) {
            if(value>0){
                tvPathwaySignaling = findViewById(R.id.tvPathwaySignaling);
                tvPathwaySignaling.setText("Attenzione: Questo percorso contiene segnalazioni!");
            }
        }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map= googleMap;
        LatLng pathwayMapStart = new LatLng(pathway.getLatStart(), pathway.getLngStart() );
        LatLng pathwayMapFinish = new LatLng(pathway.getLatFinish(), pathway.getLngFinish() );
        Marker startPath = map.addMarker(new MarkerOptions().position(pathwayMapStart).title("PuntoInizioPercorso").draggable(true));
        Marker finishPath = map.addMarker(new MarkerOptions().position(pathwayMapFinish).title("PuntoFinePercorso").draggable(true));
        CameraPosition position = new CameraPosition.Builder().target(pathwayMapStart).zoom(startPath.getRotation()).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(position));
        loadPoint(pathway.getId(),googleMap);
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
                        Toast.makeText(RuteDetailsActivityScr.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.i("onFailure:", t.getMessage());
                    }
                });

    }
    private void populateMapView(List<InterestPoints> interestPoints, GoogleMap googleMap) {
        Integer idPathway = getIntent().getIntExtra("idPathway",0);
        Log.i("populateMapView:" , idPathway.toString());
        map= googleMap;
        if(interestPoints.size()!= 0) {
            positionPoint = new CameraPosition.Builder().target(new LatLng(interestPoints.get(0).getLatitude(), interestPoints.get(0).getLongitude())).zoom(13).build();
            for (int i = 0; i <= interestPoints.size() - 1; i++) {
                Marker startMarker = map.addMarker(new MarkerOptions().position(new LatLng(interestPoints.get(i).getLatitude(), interestPoints.get(i).getLongitude())).title("interestPoint" + interestPoints.get(i).getName() + "").draggable(false));
            }
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(positionPoint));
        }else{

            positionPoint = new CameraPosition.Builder().target(new LatLng(pathway.getLatStart(), pathway.getLngStart())).zoom(13).build();
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(positionPoint));
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
            Intent intent2 = new Intent(RuteDetailsActivityScr.this, HomeActivity.class);
            intent2.putExtra("username", username);
            startActivity(intent2);
        });

        btnSignal=findViewById(R.id.btnSignal);
        btnSignal.setOnClickListener(v->{
            Intent intent4 = new Intent(RuteDetailsActivityScr.this, SignalingActivity.class);
            intent4.putExtra("username",username);
            startActivity(intent4);
        });

        ivUserTB = findViewById(R.id.btnUser);
        ivUserTB.setOnClickListener(v ->
        {
            if(googleAccountIsIn ()){
                //google
                Toast.makeText(this, "Google Account: "+username+ "", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(RuteDetailsActivityScr.this, GoogleLoginActivity.class);
                startActivity(intent2);

            }else{
                //amazon
                loadPopUp();
                amplifyCognito = new AmplifyCognito(getApplicationContext());
                amplifyCognito.userAttributes(username);}
        });

        btnChat=findViewById(R.id.btnChat);
        btnChat.setOnClickListener(v->{
            Intent intent1 = new Intent(RuteDetailsActivityScr.this, ListChatActivity.class );
            startActivity(intent1);
        });
    }
}