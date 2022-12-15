package com.example.natour2122fe.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.EditText;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.example.natour2122fe.R;

public class SignInActivity extends AppCompatActivity {
    AmplifyCognito amplifyCognito;
    EditText etUsername, etPassword;
    Button btnSignup, btnSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        amplifyCognito = new AmplifyCognito(getApplicationContext());
        setContentView(R.layout.activity_sign_in);
        findView();
        btnSignin.setOnClickListener(v -> {
            if (etUsername.getText().toString().length() == 0) {
                etUsername.setError(getResources().getString(R.string.mandatory));
            } else {
                if (etPassword.getText().toString().length() == 0) {
                    etPassword.setError(getResources().getString(R.string.mandatory));
                } else {
                    loadPopUpLoading();
                    String username = etUsername.getText().toString();
                    String password = etPassword.getText().toString();
                    amplifyCognito.signIn(username,password);
                }
            }
        });
        btnSignup.setOnClickListener(v -> amplifyCognito.loadSignup());
    }

    private void findView() {
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnSignin = findViewById(R.id.btnSignin);
        btnSignup = findViewById(R.id.btnSignup);
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
}