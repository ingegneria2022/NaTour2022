package com.example.natour2122fe;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amplifyframework.api.ApiException;
import com.example.natour2122fe.login.SignInActivity;
import com.example.natour2122fe.login.SignUpActivity;
import com.example.natour2122fe.login.google.GoogleLoginActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.Task;


public class MainActivity extends AppCompatActivity {

    Button btnSignup, btnSignin, btnGoogle;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                getApplicationContext(),
                "us-east-1:f2c9ed92-81a1-4b3c-a0ed-c47477b647ee", // ID pool di identità
                Regions.US_EAST_1 // Regione
        );

        btnGoogle=findViewById(R.id.btnGoogle);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);
        btnGoogle.setOnClickListener(v -> {
            loadPopUp();
            signInGoogle();
        });

        btnSignin = findViewById(R.id.btnSignin);
        btnSignin.setOnClickListener(v -> {
            Intent openPage1 = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(openPage1);
        });

        btnSignup = findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(v -> {
            Intent openPage2 = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(openPage2);
        });
    }

    private void loadPopUp() {
        setContentView(R.layout.activity_pop_up_loading);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.6));
    }

    void signInGoogle (){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
                 Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
            } catch (ApiException e) {
                e.printStackTrace();
            }

            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
            if(account!=null){
                String personNameGoogle = account.getId();
                navigateToSecondActivity(personNameGoogle);
            }
        }
    }
    void navigateToSecondActivity(String personNameGoogle){
        finish();
        Intent intent = new Intent(MainActivity.this, GoogleLoginActivity.class);
        intent.putExtra("username",personNameGoogle);
        startActivity(intent);
    }
}
