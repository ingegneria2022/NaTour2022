package com.example.natour2122fe;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.natour2122fe.Model.Pathway;
import com.example.natour2122fe.Retrofit.PathwayApi;
import com.example.natour2122fe.Retrofit.RetrofitService;
import com.example.natour2122fe.adapter.PathwayAdapter;
import com.example.natour2122fe.adapter.PathwayHolder;
import com.example.natour2122fe.chat.ListChatActivity;
import com.example.natour2122fe.login.AmplifyCognito;
import com.example.natour2122fe.login.google.GoogleLoginActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements PathwayHolder.OnItemListener, AdapterView.OnItemSelectedListener {

    //toolbar
    AmplifyCognito amplifyCognito;
    ImageView ivUserTB, btnSignal , btnChat;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    //fine toolbar
    PathwayAdapter pathwayAdapter;
    TextView pathwayListItemName;
    private List<Pathway> pathwayList1;
    String username;
    SearchView searchBar;
    Spinner spTypeSearch;
    FloatingActionButton btnInsPathway;
    private RecyclerView recyclerView;
    private String typeSearch;
    private boolean onSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        toolbar(username);
        searchBar();
        setAdapters();

        btnInsPathway = findViewById(R.id.btnInsPathway);
        btnInsPathway.setOnClickListener(v -> {
            Intent openPage = new Intent(this, MapPointsActivity.class);
            openPage.putExtra("username",username);
            startActivity(openPage);
        });

        pathwayListItemName =findViewById(R.id.pathwayListItem_name);
        recyclerView = findViewById(R.id.pathwayList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void searchBar() {
        searchBar=findViewById(R.id.searchBar);
        searchBar.clearFocus();
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                filtrerList(newText);
                return false;
            }
        });
    }

    private void filtrerList(String text) {
        List<Pathway>filteredList = new ArrayList<>();
        for(Pathway pathway : pathwayList1){
            if(onSelected){
                if(typeSearch.toLowerCase().contains("citta")){
                    if(pathway.getCity().toLowerCase().contains(text.toLowerCase())){
                        filteredList.add(pathway);
                    }
                }else{
                    if(typeSearch.toLowerCase().contains("livello di difficolta")){
                        if(pathway.getDifficulty().toLowerCase().contains(text.toLowerCase())){
                            filteredList.add(pathway);
                        }
                    }else{
                        if(typeSearch.toLowerCase().contains("durata")){
                            if(pathway.getDuration().contains(text.toLowerCase())){
                                filteredList.add(pathway);
                            }
                        }else{
                            if(typeSearch.toLowerCase().contains("accessibilita")){
                                if(pathway.getAccessibility().toLowerCase().contains("t")){
                                    filteredList.add(pathway);
                                }
                            }
                        }
                    }
                }
            }else{
                //Citta defoult
                if(pathway.getCity().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(pathway);
                }
            }
        }
        if(!filteredList.isEmpty()){
            pathwayAdapter.setFilteredList(filteredList);
        }
    }

    private void loadPathway() {
        RetrofitService retrofitService = new RetrofitService();
        PathwayApi pathwayApi = retrofitService.getRetrofit().create(PathwayApi.class);
        pathwayApi.viewAllPathway()
                .enqueue(new Callback<List<Pathway>>() {
                    @Override
                    public void onResponse(Call<List<Pathway>> call, Response<List<Pathway>> response) {
                        Log.i( "onResponse: ", response.body().toString());
                        populateListView(response.body());
                    }
                    @Override
                    public void onFailure(Call<List<Pathway>> call, Throwable t) {
                        Toast.makeText(HomeActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.i("onFailure:", t.getMessage());
                    }
                });
    }

    private void populateListView(List<Pathway> pathwayList) {
        pathwayList1= pathwayList;
        pathwayAdapter = new PathwayAdapter(pathwayList,this);
        recyclerView.setAdapter(pathwayAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPathway();
    }

    private void setAdapters() {
        spTypeSearch = findViewById(R.id.spTypeSearch);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.TypeSearch, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTypeSearch.setAdapter(adapter);
        spTypeSearch.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemClick(int position) {
        Pathway pathway = pathwayList1.get(position);
        Intent intent = new Intent(this, RuteDetailsActivityScr.class);
        intent.putExtra("username",username);
        intent.putExtra("pathwayName", pathway);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        onSelected=true;
       typeSearch = adapterView.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {    }

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
        btnSignal=findViewById(R.id.btnSignal);
        btnSignal.setOnClickListener(v->{
            Intent intent4 = new Intent(HomeActivity.this, SignalingActivity.class);
            intent4.putExtra("username",username);
            startActivity(intent4);
        });
        ivUserTB = findViewById(R.id.btnUser);
        ivUserTB.setOnClickListener(v ->
        {
            if(googleAccountIsIn ()){
                //google
                Toast.makeText(this, "Google Account: "+username+ "", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(HomeActivity.this, GoogleLoginActivity.class);
                startActivity(intent2);
            }else{
                //amazon
                loadPopUpLoading();
                amplifyCognito = new AmplifyCognito(getApplicationContext());
                amplifyCognito.userAttributes(username);}
        });
        btnChat=findViewById(R.id.btnChat);
        btnChat.setOnClickListener(v->{
            Intent intent1 = new Intent(HomeActivity.this, ListChatActivity.class );
            startActivity(intent1);
        });
    }

}