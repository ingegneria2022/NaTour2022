package com.example.natour2122fe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.natour2122fe.Model.Photo;
import com.example.natour2122fe.Retrofit.PathwayApi;
import com.example.natour2122fe.Retrofit.RetrofitService;
import com.example.natour2122fe.chat.ListChatActivity;
import com.example.natour2122fe.login.AmplifyCognito;
import com.example.natour2122fe.login.google.GoogleLoginActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import java.io.File;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsPhotoActivity extends AppCompatActivity {

    private static final long MAX_BYTE = 5242880;
    ImageView loadPhoto, photoView;
    Uri selectedImage;
    String fileName;
    Button btnInsPhotoPathway;
    String username;
    Photo newPhoto=new Photo();
    int idPathway;
    //toolbar
    AmplifyCognito amplifyCognito;
    ImageView ivUserTB, btnSignal , btnChat, btnHomeTB;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    //fine toolbar


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ins_photo);

        //Intent intent2 = new Intent();
         idPathway= getIntent().getIntExtra("idPathway", 0);
        username = getIntent().getStringExtra("username");
        Log.i("onCreate: ", username+idPathway);
        toolbar(username);

        findView();

        loadPhoto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 4);
        });

        btnInsPhotoPathway.setOnClickListener(v->{
            //loadPopUpLoading();

            newPhoto.setIdPathway(idPathway);
            newPhoto.setUsername(username);
            newPhoto.setName(fileName);
            Log.i( "onCreate: ", newPhoto.toString());
            saveImg(newPhoto);
        });

    }

    private void saveImg(Photo newPhoto) {
        RetrofitService retrofitService = new RetrofitService();
        PathwayApi pathwayApi = retrofitService.getRetrofit().create(PathwayApi.class);
        pathwayApi.addPhoto(newPhoto)
                .enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if( response.body() != null) {
                    try {
                        Log.i("onResponse: ", response.body().string());
                        loadPhotoActivity();
                    } catch (IOException e) {
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("TAG", "onResponse: no" + t);
            }
        });
        //Activity Visualizza Foto

    }

    private void findView() {
        btnInsPhotoPathway=findViewById(R.id.btnInsPhotoPathway);
        loadPhoto = findViewById(R.id.loadPhoto);
        photoView = findViewById(R.id.photoView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data!=null){
            selectedImage = data.getData();
            File file = new File(selectedImage.getLastPathSegment());
            Log.i( "saveImg: "+file.length(),selectedImage.getPath());
            if(file.length()<MAX_BYTE){
                saveImgOnBucket(selectedImage);
                photoView.setImageURI(selectedImage);
                //onResult=true;
            }else{
                Toast.makeText(this, "Immagine troppo grande", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void saveImgOnBucket(Uri selectedImage) {
        RetrofitService retrofitService = new RetrofitService();
        PathwayApi pathwayApi = retrofitService.getRetrofit().create(PathwayApi.class);
        File path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM);
        try {
            // Make sure the Pictures directory exists.
            path.mkdirs();
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file = new File(selectedImage.getLastPathSegment());
        // creates RequestBody instance from file
        RequestBody requestFile = RequestBody.create(MediaType.parse("photo"), file);
        // MultipartBody.Part is used to send also the actual filename
        MultipartBody.Part body = MultipartBody.Part.createFormData("photo", file.getName(), requestFile);
        String keyStr = "photo";
        // executes the request
        pathwayApi.update(keyStr,body)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if( response.body() != null) {
                            try {
                                String body = response.body().string();
                                fileName = body.replace("File uploaded : ", "");

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.i("TAG", "onResponse: no" + t);
                    }
                });
    }

    private void loadPhotoActivity() {
        Intent openPage4 = new Intent(this, PhotoActivity.class);
        openPage4.putExtra("idPathway",idPathway);
        openPage4.putExtra("username", username);
        startActivity(openPage4);
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
            Intent intent2 = new Intent(InsPhotoActivity.this, HomeActivity.class);
            intent2.putExtra("username", username);
            startActivity(intent2);
        });
        btnSignal=findViewById(R.id.btnSignal);
        btnSignal.setOnClickListener(v->{
            Intent intent4 = new Intent(InsPhotoActivity.this, SignalingActivity.class);
            intent4.putExtra("username",username);
            startActivity(intent4);
        });
        ivUserTB = findViewById(R.id.btnUser);
        ivUserTB.setOnClickListener(v ->
        {
            if(googleAccountIsIn ()){
                //google
                Toast.makeText(this, "Google Account: "+username+ "", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(InsPhotoActivity.this, GoogleLoginActivity.class);
                startActivity(intent2);
            }else{
                //amazon
                loadPopUpLoading();
                amplifyCognito = new AmplifyCognito(getApplicationContext());
                amplifyCognito.userAttributes(username);}
        });
        btnChat=findViewById(R.id.btnChat);
        btnChat.setOnClickListener(v->{
            Intent intent1 = new Intent(InsPhotoActivity.this, ListChatActivity.class );
            startActivity(intent1);
        });
    }
}