package com.example.natour2122fe.login.google;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.natour2122fe.HomeActivity;
import com.example.natour2122fe.InsFeedbackActivity;
import com.example.natour2122fe.InsPathwayActivity;
import com.example.natour2122fe.MainActivity;
import com.example.natour2122fe.Model.User;
import com.example.natour2122fe.R;
import com.example.natour2122fe.Retrofit.PathwayApi;
import com.example.natour2122fe.Retrofit.RetrofitService;
import com.example.natour2122fe.SignalingActivity;
import com.example.natour2122fe.chat.ListChatActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoogleLoginActivity extends AppCompatActivity {

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name, email, id, familyName;
    Button btnSingOutGoogle;
    ImageView btnHomeTB,btnSignal , btnChat;;
    ImageView ivPersonFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_login);
        findView();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        String personUsenameGoogle = googlePersonAttribute();
        btnSingOutGoogle.setOnClickListener(v -> signOutGoogle());
        toolbar(personUsenameGoogle);
    }

    private void findView() {
        name = findViewById(R.id.tvName);
        email = findViewById(R.id.tvEmail);
        id = findViewById(R.id.tvId);
        familyName = findViewById(R.id.tvFamilyName);
        btnSingOutGoogle = findViewById(R.id.btnSignOutGoogle);
        ivPersonFoto = findViewById(R.id.ivPersonFoto);
    }

    void signOutGoogle() {
        gsc.signOut().addOnCompleteListener(task -> {
            finish();
            startActivity(new Intent(GoogleLoginActivity.this, MainActivity.class));
        });
    }

    String googlePersonAttribute(){
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            String personNameGoogle = account.getGivenName();
            String personFamilyNameGoogle = account.getFamilyName();
            String personEmailGoogle = account.getEmail();
            String personUsenameGoogle = account.getId();
            Uri personPhoto=account.getPhotoUrl();
            Glide.with(GoogleLoginActivity.this)
                    .load(personPhoto)
                            .into(ivPersonFoto);

            User user= new User();
            user.setEmail(personEmailGoogle);
            user.setName(personNameGoogle);
            user.setSurname(personFamilyNameGoogle);
            user.setUsername(personUsenameGoogle);
            if(account.getPhotoUrl()!=null){
                user.setPhoto(personPhoto.toString());
            }else{
                String noPhoto="https://pathway-image.s3.amazonaws.com/1667403320228_nophoto.png";
                Glide.with(GoogleLoginActivity.this)
                        .load(noPhoto)
                        .into(ivPersonFoto);
            }
            saveUser(user);
            id.setText(personUsenameGoogle);
            name.setText(personNameGoogle);
            familyName.setText(personFamilyNameGoogle);
            email.setText(personEmailGoogle);

            return personUsenameGoogle;
        }
        return null;
    }

    private void saveUser(User user) {
        //retrofit
        RetrofitService retrofitService = new RetrofitService();
        PathwayApi pathwayApi = retrofitService.getRetrofit().create(PathwayApi.class);
        pathwayApi.addUser(user);
    }

    public void toolbar(String personUsenameGoogle) {

        btnHomeTB = findViewById(R.id.btnHome);
        btnHomeTB.setOnClickListener(v ->
        {
            Intent intent2 = new Intent(GoogleLoginActivity.this, HomeActivity.class);
            intent2.putExtra("username", personUsenameGoogle);
            startActivity(intent2);
        });

        btnSignal=findViewById(R.id.btnSignal);
        btnSignal.setOnClickListener(v->{
            Intent intent4 = new Intent(GoogleLoginActivity.this, SignalingActivity.class);
            intent4.putExtra("username",personUsenameGoogle);
            startActivity(intent4);
        });

        btnChat=findViewById(R.id.btnChat);
        btnChat.setOnClickListener(v->{
            Intent intent1 = new Intent(GoogleLoginActivity.this, ListChatActivity.class );
            startActivity(intent1);
        });

    }
}

