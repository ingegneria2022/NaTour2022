package com.example.natour2122fe.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.natour2122fe.R;

public class ConfirmActivity extends AppCompatActivity {
    AmplifyCognito amplifyCognito;
    EditText etCode;
    Button btnVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        amplifyCognito = new AmplifyCognito(getApplicationContext());
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        etCode = findViewById(R.id.etCode);
        btnVerify = findViewById(R.id.btnVerify);
        btnVerify.setOnClickListener(v -> {
            String code = etCode.getText().toString();
            amplifyCognito.confirm(username, code);
        });
    }
}