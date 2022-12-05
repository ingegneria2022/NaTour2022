package com.example.natour2122fe.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.TextView;

import com.example.natour2122fe.R;
import com.example.natour2122fe.VisPathwaySignalitedActivity;
import com.example.natour2122fe.login.AmplifyCognito;

public class AdminActivity extends AppCompatActivity {

    AmplifyCognito amplifyCognito;
    TextView tvAdmin;
    Button btnSnout, btnVisSign;

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