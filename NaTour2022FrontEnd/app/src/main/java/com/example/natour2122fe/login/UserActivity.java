package com.example.natour2122fe.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.natour2122fe.HomeActivity;
import com.example.natour2122fe.R;
import com.example.natour2122fe.SignalingActivity;
import com.example.natour2122fe.chat.ListChatActivity;
import com.example.natour2122fe.login.google.GoogleLoginActivity;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.net.URI;

public class UserActivity extends AppCompatActivity {

    Button btnSnout;
    ImageView ivPhotoUser;
    TextView tvEmailUA, tvNameUA,tvSurnameUA,tvUsernameUA;
    //toolbar
    AmplifyCognito amplifyCognito;
    ImageView btnHomeTB, btnSignal , btnChat;
    //fine toolbar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        amplifyCognito = new AmplifyCognito(getApplicationContext());
        setContentView(R.layout.activity_user);
        findView();


        /* recupero i dati dal metodo nella classe amplify*/
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String email = intent.getStringExtra("email");
        String name = intent.getStringExtra("name");
        String surname = intent.getStringExtra("surname");


        toolbar(username);
        tvUsernameUA.setText(username);
        tvEmailUA.setText(email);
        tvNameUA.setText(name);
        tvSurnameUA.setText(surname);

        /* button logout */
        btnSnout.setOnClickListener(v -> {
            loadPopUp();
            amplifyCognito.signOut()
            ;});

    }

    private void findView() {
        btnHomeTB = findViewById(R.id.btnHome);
        btnSnout = findViewById(R.id.btnSignout);
        tvUsernameUA=findViewById(R.id.tvUsernameUA);
        tvEmailUA=findViewById(R.id.tvEmailUA);
        tvNameUA=findViewById(R.id.tvNameUA);
        tvSurnameUA=findViewById(R.id.tvSurnameUA);
        ivPhotoUser=findViewById(R.id.ivPhotoUser);
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
    public void toolbar(String personUsenameGoogle) {

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        btnHomeTB.setOnClickListener(v ->
        {
            Intent intent2 = new Intent(UserActivity.this, HomeActivity.class);
            intent2.putExtra("username", personUsenameGoogle);
            startActivity(intent2);
        });

        btnSignal=findViewById(R.id.btnSignal);
        btnSignal.setOnClickListener(v->{
            Intent intent4 = new Intent(UserActivity.this, SignalingActivity.class);
            intent4.putExtra("username",username);
            //Log.i("toolbar: ", username);
            startActivity(intent4);
        });

        btnChat=findViewById(R.id.btnChat);
        btnChat.setOnClickListener(v->{
            Intent intent1 = new Intent(UserActivity.this, ListChatActivity.class );
            //Log.i("toolbar: ", username);
            startActivity(intent1);
        });

    }

}